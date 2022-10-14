package org.skytec.clan.dto;

import java.util.Optional;
import org.skytec.clan.core.constant.Const;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 10:11 AM
 */

public class CompleteTaskDto implements Dto {
  public long clanId;
  public long taskId;

  @Override
  public Optional<Dto> fromPayload(String payload) {
    final String[] data = payload.split(Const.PAYLOAD_SEPARATOR);
    if (data.length != 2) {
      return Optional.empty();
    }

    final CompleteTaskDto completeTaskDto = new CompleteTaskDto();
    try {
      completeTaskDto.clanId = Long.parseLong(data[0]);
      completeTaskDto.taskId = Long.parseLong(data[1]);
    } catch (Exception e) {
      return Optional.empty();
    }

    return Optional.of(completeTaskDto);
  }
}
