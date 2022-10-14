package org.skytec.clan.core.controller.registrar;

import java.util.ArrayList;
import java.util.List;
import org.skytec.clan.core.controller.Controller;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/14/22 - 6:20 PM
 */

public class ControllerRegistrar {
  private static ControllerRegistrar instance;
  private final List<Controller> buffer = new ArrayList<>();
  private Controller[] controllers;

  public static ControllerRegistrar begin() {
    if (instance == null) {
      instance = new ControllerRegistrar();
    }
    return instance;
  }

  public ControllerRegistrar register(Controller... list) {
    buffer.addAll(List.of(list));
    return this;
  }

  public ControllerRegistrar commit() {
    controllers = new Controller[buffer.size()];
    buffer.toArray(controllers);
    return this;
  }

  public Controller[] registered() {
    return controllers;
  }
}
