package org.skytec.clan.logger;

import org.skytec.clan.object.wallet.Wallet;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/12/22 - 2:50 PM
 */

public class TransactionLogUnit extends LogUnit {
  private final long giverWalletId;
  private final long giverWalletVal;
  private final long oldGiverWalletVal;
  private final long recipientWalletId;
  private final long recipientWalletVal;
  private final long oldRecipientWalletVal;
  private final long value;
  private final String reason;

  public TransactionLogUnit(Wallet giverWallet, long oldGiverWalletVal,
                            Wallet recipientWallet, long oldRecipientWalletVal,
                            long value, String reason) {
    super();
    this.giverWalletId = giverWallet.id();
    this.giverWalletVal = giverWallet.val();
    this.oldGiverWalletVal = oldGiverWalletVal;
    this.recipientWalletId = recipientWallet.id();
    this.recipientWalletVal = recipientWallet.val();
    this.oldRecipientWalletVal = oldRecipientWalletVal;
    this.value = value;
    this.reason = reason;
  }

  @Override
  public String build() {
    return "at(" + timePoint + "), " +
      "giver(walletId = " + giverWalletId + ", before = " + oldGiverWalletVal + ", " +
      "after = " + giverWalletVal + "), " +
      "recip(walletId = " + recipientWalletId + ", before = " + oldRecipientWalletVal + ", " +
      "after = " + recipientWalletVal + "), " +
      "value(" + value + "), reason(" + reason + ")";
  }
}
