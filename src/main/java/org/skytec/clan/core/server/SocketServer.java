package org.skytec.clan.core.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.skytec.clan.context.ctx;
import org.skytec.clan.core.server.configuration.ServerConfiguration;
import org.skytec.clan.core.server.processor.SocketProcessor;
import org.skytec.clan.logger.ServerStartedLogUnit;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 11:44 AM
 */

public class SocketServer {
  private final ExecutorService executorService = Executors.newFixedThreadPool(16);
  private final ServerConfiguration configuration;
  private boolean isRunning = true;

  public SocketServer(ServerConfiguration configuration) {
    this.configuration = configuration;
  }

  public void start() {
    try (final ServerSocket serverSocket = new ServerSocket(configuration.port)) {
      ctx.logQueue.add(new ServerStartedLogUnit(configuration.port));
      while (isRunning) {
        final Socket socket = serverSocket.accept();
        executorService.execute(() -> new SocketProcessor(socket, configuration.controllerRegistrar).run());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void stop() {
    isRunning = false;
  }
}