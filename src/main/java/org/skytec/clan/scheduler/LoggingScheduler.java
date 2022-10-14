package org.skytec.clan.scheduler;

import org.skytec.clan.context.ctx;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/12/22 - 2:33 PM
 */

public class LoggingScheduler implements Scheduler {
  @Override
  public void run() {
    ctx.loggingService.log();
  }
}
