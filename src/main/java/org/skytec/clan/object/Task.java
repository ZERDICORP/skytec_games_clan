package org.skytec.clan.object;

import org.skytec.clan.object.wallet.BottomlessWallet;
import org.skytec.clan.object.wallet.Wallet;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 9:39 AM
 */

public class Task implements GameObject<Long>, Payable {
  private static long staticId;
  private final long id;
  private final String description;
  private final Wallet wallet;

  public Task(String description, long gold) {
    this.description = description;
    wallet = new BottomlessWallet(gold);
    id = ++staticId;
  }

  @Override
  public Long id() {
    return id;
  }

  public String description() {
    return description;
  }

  @Override
  public Wallet wallet() {
    return wallet;
  }
}
