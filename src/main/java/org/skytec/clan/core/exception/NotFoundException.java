package org.skytec.clan.core.exception;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 11:17 AM
 */

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
