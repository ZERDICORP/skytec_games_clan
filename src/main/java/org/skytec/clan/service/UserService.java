package org.skytec.clan.service;

import org.skytec.clan.context.ctx;
import org.skytec.clan.core.exception.NotFoundException;
import org.skytec.clan.object.User;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 1:13 PM
 */

public class UserService {
  public User get(long id) {
    return ctx.userStorage.findById(id)
      .orElseThrow(() -> new NotFoundException("no user with id " + id));
  }
}
