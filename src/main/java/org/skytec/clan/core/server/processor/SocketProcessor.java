package org.skytec.clan.core.server.processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.skytec.clan.core.controller.registrar.ControllerRegistrar;
import org.skytec.clan.core.request.Request;
import org.skytec.clan.core.request.processor.RequestProcessor;
import org.skytec.clan.core.server.packet.SocketServerPacket;
import org.skytec.clan.core.server.util.SocketServerUtils;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 11:58 AM
 */

public class SocketProcessor implements Runnable {
  private final Socket socket;
  private final RequestProcessor requestProcessor;
  private final byte[] firstSegmentBuffer = new byte[8000];

  public SocketProcessor(Socket socket, ControllerRegistrar controllerRegistrar) {
    this.socket = socket;
    requestProcessor = new RequestProcessor(controllerRegistrar);
  }

  @Override
  public void run() {
    try (final InputStream inputStream = socket.getInputStream();
         final OutputStream outputStream = socket.getOutputStream()) {
      final Optional<SocketServerPacket> parsedPacket = parsePacket(inputStream);
      if (parsedPacket.isEmpty()) {
        outputStream.write("bad request".getBytes());
        return;
      }

      final SocketServerPacket socketServerPacket = parsedPacket.get();

      final Optional<Request> parsedRequest = Request.parse(socketServerPacket.bodyAsString());
      if (parsedRequest.isEmpty()) {
        outputStream.write("bad request body".getBytes());
        return;
      }

      final Request request = parsedRequest.get();
      try {
        requestProcessor.process(request);
      } catch (RuntimeException e) {
        outputStream.write(e.getMessage().getBytes());
        return;
      }

      outputStream.write("ok".getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Optional<SocketServerPacket> parsePacket(InputStream inputStream) throws IOException {
    final int firstSegmentSize = inputStream.read(firstSegmentBuffer);
    if (firstSegmentSize == -1) {
      return Optional.empty();
    }

    final int metaSize = SocketServerUtils.getMetaSize(firstSegmentBuffer, firstSegmentSize);

    final SocketServerPacket socketServerPacket = new SocketServerPacket();
    if (!socketServerPacket.parseMeta(new String(firstSegmentBuffer, 0, metaSize, StandardCharsets.UTF_8))) {
      return Optional.empty();
    }

    final int bodyBytesLengthInFirstSegment = firstSegmentSize - (metaSize + 1);
    for (int i = 0; i < bodyBytesLengthInFirstSegment && i < socketServerPacket.body().length; ++i) {
      socketServerPacket.body()[i] = firstSegmentBuffer[i + (metaSize + 1)];
    }

    int offset = bodyBytesLengthInFirstSegment;
    int segmentSize;

    while (offset < socketServerPacket.body().length &&
      (segmentSize = inputStream.read(socketServerPacket.body(), offset, socketServerPacket.body().length - offset)) > 0) {
      offset += segmentSize;
    }

    return Optional.of(socketServerPacket);
  }
}
