package org.skytec.clan.logger;

import java.time.LocalDateTime;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/12/22 - 2:49 PM
 */

public abstract class LogUnit {
  protected final LocalDateTime timePoint;

  public LogUnit() {
    this.timePoint = LocalDateTime.now();
  }

  public LocalDateTime timePoint() {
    return timePoint;
  }

  public abstract String build();
}
