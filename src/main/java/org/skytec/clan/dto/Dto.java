package org.skytec.clan.dto;

import java.util.Optional;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 10:08 AM
 */

public interface Dto {
  Optional<Dto> fromPayload(String payload);
}
