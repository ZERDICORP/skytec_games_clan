package org.skytec.clan.core.server.util;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 12:18 PM
 */

public class SocketServerUtils {
  public static int getMetaSize(byte[] b, int length) {
    for (int i = 0; i < length; ++i)
      if (b[i] == '\n') {
        return i;
      }
    return b.length;
  }
}
