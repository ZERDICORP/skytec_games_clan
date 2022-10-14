package org.skytec.clan.object.wallet;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 3:20 PM
 */

public class BottomlessWallet extends Wallet {
  private static long staticId;
  private final long id;
  private final long gold;

  public BottomlessWallet(long gold) {
    this.gold = gold;
    id = --staticId;
  }

  public long val() {
    return gold;
  }

  public long drop(long value) {
    return value;
  }

  @Override
  public void add(long value) {
  }

  @Override
  public Long id() {
    return id;
  }
}
