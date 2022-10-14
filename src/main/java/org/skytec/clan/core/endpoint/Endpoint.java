package org.skytec.clan.core.endpoint;

import java.util.Optional;
import java.util.function.Consumer;
import org.skytec.clan.core.request.Request;
import org.skytec.clan.dto.Dto;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 10:28 AM
 */

public class Endpoint<T extends Dto> {
  private T dto;
  private final Consumer<T> closure;

  private Endpoint(Consumer<T> closure) {
    this.closure = closure;
  }

  public Endpoint<T> withDto(T dto) {
    this.dto = dto;
    return this;
  }

  public static <T extends Dto> Endpoint<T> byClosure(Consumer<T> method) {
    return new Endpoint<>(method);
  }

  public boolean proceed(Request request) {
    final Optional<Dto> dto = this.dto.fromPayload(request.payload());
    if (dto.isEmpty()) {
      return false;
    }

    closure.accept((T) dto.get());
    return true;
  }
}
