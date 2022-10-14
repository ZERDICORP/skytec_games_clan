package org.skytec.clan.logger.queue;

import java.util.LinkedList;
import java.util.List;
import org.skytec.clan.logger.LogUnit;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/12/22 - 2:48 PM
 */

public class LogQueue {
  private List<LogUnit> queue = new LinkedList<>();

  public void add(LogUnit logUnit) {
    queue.add(logUnit);
  }

  public List<LogUnit> available() {
    final List<LogUnit> copy = queue;
    queue = new LinkedList<>();
    return copy;
  }
}