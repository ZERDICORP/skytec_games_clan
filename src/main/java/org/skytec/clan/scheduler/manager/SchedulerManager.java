package org.skytec.clan.scheduler.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.skytec.clan.scheduler.LoggingScheduler;
import org.skytec.clan.scheduler.Scheduler;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/12/22 - 2:29 PM
 */

public class SchedulerManager {
  private static SchedulerManager instance;
  private final List<Scheduler> schedulers = new ArrayList<>();
  private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
  private long delay;

  {
    schedulers.add(new LoggingScheduler());
  }

  public static SchedulerManager begin() {
    if (instance == null) {
      instance = new SchedulerManager();
    }
    return instance;
  }

  public SchedulerManager withDelay(long delay) {
    this.delay = delay;
    return this;
  }

  public void start() {
    schedulers.forEach(
      scheduler -> scheduledExecutorService.scheduleAtFixedRate(scheduler, 0, delay, TimeUnit.SECONDS));
  }
}
