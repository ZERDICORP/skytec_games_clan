package org.skytec.clan.object.wallet;

import org.skytec.clan.object.GameObject;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 3:23 PM
 */

public abstract class Wallet implements GameObject<Long> {
  public abstract long val();

  public abstract long drop(long value);

  public abstract void add(long value);
}
