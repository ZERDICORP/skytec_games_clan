package org.skytec.clan.core.server.builder;

import org.skytec.clan.core.controller.registrar.ControllerRegistrar;
import org.skytec.clan.core.server.SocketServer;
import org.skytec.clan.core.server.configuration.ServerConfiguration;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 11:48 AM
 */

public class ServerBuilder {
  private static ServerBuilder instance;
  private static ServerConfiguration configuration;

  public static ServerBuilder begin() {
    if (instance == null) {
      instance = new ServerBuilder();
      configuration = new ServerConfiguration();
    }
    return instance;
  }

  public SocketServer build() {
    return new SocketServer(configuration);
  }

  public ServerBuilder withPort(int port) {
    configuration.port = port;
    return instance;
  }

  public ServerBuilder withControllerRegistrar(ControllerRegistrar controllerRegistrar) {
    configuration.controllerRegistrar = controllerRegistrar;
    return instance;
  }
}