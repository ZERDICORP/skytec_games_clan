package org.skytec.clan.core.exception;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 11:28 AM
 */

public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }
}
