package org.skytec.clan.object;

import org.skytec.clan.object.wallet.ClassicWallet;
import org.skytec.clan.object.wallet.Wallet;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 1:12 PM
 */

public class User implements GameObject<Long>, Payable {
  private static long staticId;
  private final long id;
  private final String name;
  private final Wallet wallet = new ClassicWallet();

  public User(String name, long gold) {
    this.name = name;
    wallet.add(gold);
    id = ++staticId;
  }

  @Override
  public Long id() {
    return id;
  }

  public String name() {
    return name;
  }

  @Override
  public Wallet wallet() {
    return wallet;
  }
}
