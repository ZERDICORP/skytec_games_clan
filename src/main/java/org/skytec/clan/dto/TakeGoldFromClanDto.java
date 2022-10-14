package org.skytec.clan.dto;

import java.util.Optional;
import org.skytec.clan.core.constant.Const;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 10:08 AM
 */

public class TakeGoldFromClanDto implements Dto {
  public long userId;
  public long clanId;
  public long gold;

  @Override
  public Optional<Dto> fromPayload(String payload) {
    final String[] data = payload.split(Const.PAYLOAD_SEPARATOR);
    if (data.length != 3) {
      return Optional.empty();
    }

    final TakeGoldFromClanDto addUserGoldDto = new TakeGoldFromClanDto();
    try {
      addUserGoldDto.userId = Long.parseLong(data[0]);
      addUserGoldDto.clanId = Long.parseLong(data[1]);
      addUserGoldDto.gold = Integer.parseInt(data[2]);
      if (gold < 0) {
        return Optional.empty();
      }
    } catch (Exception e) {
      return Optional.empty();
    }

    return Optional.of(addUserGoldDto);
  }
}
