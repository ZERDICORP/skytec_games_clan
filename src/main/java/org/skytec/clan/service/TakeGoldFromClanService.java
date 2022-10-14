package org.skytec.clan.service;

import org.skytec.clan.context.ctx;
import org.skytec.clan.object.Clan;
import org.skytec.clan.object.User;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 9:31 AM
 */

public class TakeGoldFromClanService {
  public void takeGoldFromClan(long userId, long clanId, long gold) {
    final User user = ctx.userService.get(userId);
    final Clan clan = ctx.clanService.get(clanId);
    ctx.transactionService.commit(user, clan, gold, "take from clan wallet");
  }
}
