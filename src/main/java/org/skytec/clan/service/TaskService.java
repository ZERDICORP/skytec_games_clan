package org.skytec.clan.service;

import org.skytec.clan.context.ctx;
import org.skytec.clan.core.exception.NotFoundException;
import org.skytec.clan.object.Clan;
import org.skytec.clan.object.Task;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 9:33 AM
 */

public class TaskService {
  public Task get(long id) {
    return ctx.taskStorage.findById(id)
      .orElseThrow(() -> new NotFoundException("no task with id " + id));
  }

  public void complete(long clanId, long taskId) {
    final Task task = get(taskId);
    final Clan clan = ctx.clanService.get(clanId);
    ctx.transactionService.commit(clan, task, task.wallet().val(), "complete task");
  }
}
