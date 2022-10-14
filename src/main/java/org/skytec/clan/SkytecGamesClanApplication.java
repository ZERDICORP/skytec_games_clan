package org.skytec.clan;

import org.skytec.clan.controller.ClanController;
import org.skytec.clan.core.controller.registrar.ControllerRegistrar;
import org.skytec.clan.core.server.builder.ServerBuilder;
import org.skytec.clan.scheduler.manager.SchedulerManager;

public class SkytecGamesClanApplication {
  public static void main(String[] args) {
    SchedulerManager.begin()
      .withDelay(1)
      .start();

    ServerBuilder.begin()
      .withPort(8000)
      .withControllerRegistrar(ControllerRegistrar.begin()
        .register(new ClanController())
        .commit())
      .build()
      .start();
  }
}
