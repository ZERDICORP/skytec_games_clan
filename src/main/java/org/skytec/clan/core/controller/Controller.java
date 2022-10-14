package org.skytec.clan.core.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.skytec.clan.core.endpoint.Endpoint;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 10:01 AM
 */

public abstract class Controller {
  private final Map<String, Endpoint<?>> endpoints = new HashMap<>();

  public void bind(String key, Endpoint<?> endpoint) {
    endpoints.put(key, endpoint);
  }

  public Optional<Endpoint<?>> resolve(String key) {
    return Optional.ofNullable(endpoints.get(key));
  }
}
