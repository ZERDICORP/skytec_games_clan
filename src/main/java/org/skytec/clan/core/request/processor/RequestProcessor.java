package org.skytec.clan.core.request.processor;

import java.util.Optional;
import org.skytec.clan.core.controller.Controller;
import org.skytec.clan.core.controller.registrar.ControllerRegistrar;
import org.skytec.clan.core.endpoint.Endpoint;
import org.skytec.clan.core.exception.BadRequestException;
import org.skytec.clan.core.exception.NotFoundException;
import org.skytec.clan.core.request.Request;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 12:58 PM
 */

public class RequestProcessor {
  private final Controller[] controllers;

  public RequestProcessor(ControllerRegistrar controllerRegistrar) {
    this.controllers = controllerRegistrar.registered();
  }

  public void process(Request request) {
    final Endpoint<?> endpoint = resolveEndpoint(request.path())
      .orElseThrow(() -> new NotFoundException("path \"" + request.path() + "\" not found"));
    if (!endpoint.proceed(request)) {
      throw new BadRequestException("invalid payload for path \"" + request.path() + "\"");
    }
  }

  private Optional<Endpoint<?>> resolveEndpoint(String path) {
    for (final Controller controller : controllers) {
      final Optional<Endpoint<?>> foundEndpoint = controller.resolve(path);
      if (foundEndpoint.isEmpty()) {
        continue;
      }
      return foundEndpoint;
    }
    return Optional.empty();
  }
}
