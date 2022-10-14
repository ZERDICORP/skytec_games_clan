package org.skytec.clan.core.server.configuration;

import org.skytec.clan.core.controller.registrar.ControllerRegistrar;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 11:48 AM
 */

public class ServerConfiguration {
  public int port = 8000;
  public ControllerRegistrar controllerRegistrar = ControllerRegistrar.begin()
    .commit();
}
