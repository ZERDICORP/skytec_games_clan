package org.skytec.clan.logger;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/12/22 - 3:04 PM
 */

public class ServerStartedLogUnit extends LogUnit {
  private final int port;

  public ServerStartedLogUnit(int port) {
    super();
    this.port = port;
  }

  @Override
  public String build() {
    return "Server started on port " + port + "..";
  }
}
