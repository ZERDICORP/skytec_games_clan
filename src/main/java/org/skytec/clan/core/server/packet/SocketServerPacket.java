package org.skytec.clan.core.server.packet;

import java.nio.charset.StandardCharsets;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 12:07 PM
 */

public class SocketServerPacket {
  private byte[] body;

  public byte[] body() {
    return body;
  }

  public String bodyAsString() {
    return new String(body, 0, body.length, StandardCharsets.UTF_8);
  }

  public boolean parseMeta(String raw) {
    final String[] data = raw.split(";");
    if (data.length == 0) {
      return false;
    }

    try {
      body = new byte[Integer.parseInt(data[0])];
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
