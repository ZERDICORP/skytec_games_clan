package org.skytec.clan.core.exception;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 1:21 PM
 */

public class ConflictException extends RuntimeException {
  public ConflictException(String message) {
    super(message);
  }
}
