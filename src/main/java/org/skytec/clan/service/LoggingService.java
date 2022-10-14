package org.skytec.clan.service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.skytec.clan.context.ctx;
import org.skytec.clan.logger.LogUnit;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/13/22 - 9:30 PM
 */

public class LoggingService {
  private final ExecutorService executorService = Executors.newFixedThreadPool(16);

  public void log() {
    final List<LogUnit> queue = ctx.logQueue.available();
    executorService.execute(() -> process(queue));
  }

  private void process(List<LogUnit> queue) {
    queue.stream()
      .sorted(Comparator.comparing(LogUnit::timePoint))
      .forEach(logUnit -> System.out.println(logUnit.build()));
  }
}
