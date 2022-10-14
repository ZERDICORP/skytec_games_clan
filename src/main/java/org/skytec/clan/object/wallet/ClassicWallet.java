package org.skytec.clan.object.wallet;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 3:13 PM
 */

public class ClassicWallet extends Wallet {
  private static long staticId;
  private final long id;
  private volatile long gold;

  public ClassicWallet() {
    id = ++staticId;
  }

  public long val() {
    return gold;
  }

  public long drop(long value) {
    gold -= value;
    return value;
  }

  public void add(long value) {
    gold += value;
  }

  @Override
  public Long id() {
    return id;
  }
}
