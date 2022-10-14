package org.skytec.clan.core.request;

import java.util.Optional;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 9:49 AM
 */

public class Request {
  private final String path;
  private final String payload;

  public Request(String path, String payload) {
    this.path = path;
    this.payload = payload;
  }

  public static Optional<Request> parse(String plain) {
    final String[] data = plain.split("\n");
    if (data.length != 2) {
      return Optional.empty();
    }
    return Optional.of(new Request(data[0], data[1]));
  }

  public String path() {
    return path;
  }

  public String payload() {
    return payload;
  }
}
