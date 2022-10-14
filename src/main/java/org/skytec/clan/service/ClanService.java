package org.skytec.clan.service;

import org.skytec.clan.context.ctx;
import org.skytec.clan.core.exception.NotFoundException;
import org.skytec.clan.object.Clan;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 9:06 AM
 */

public class ClanService {
  public Clan get(long id) {
    return ctx.clanStorage.findById(id)
      .orElseThrow(() -> new NotFoundException("no clan with id " + id));
  }
}
