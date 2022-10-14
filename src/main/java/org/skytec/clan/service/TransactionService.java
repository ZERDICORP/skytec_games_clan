package org.skytec.clan.service;

import org.skytec.clan.context.ctx;
import org.skytec.clan.core.exception.ConflictException;
import org.skytec.clan.logger.TransactionLogUnit;
import org.skytec.clan.object.Payable;
import org.skytec.clan.object.wallet.Wallet;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 2:56 PM
 */

public class TransactionService {
  public void commit(Payable recipient, Payable giver, long value, String reason) {
    final Wallet recipientWallet = recipient.wallet();
    final Wallet giverWallet = giver.wallet();

    final long oldRecipientWalletVal = recipientWallet.val();
    final long oldGiverWalletVal = giverWallet.val();

    if (giverWallet.val() - value < 0) {
      throw new ConflictException("not enough gold");
    }

    if (!(recipientWallet.val() == oldRecipientWalletVal && giverWallet.val() == oldGiverWalletVal)) {
      // rollback
      commit(recipient, giver, value, reason);
      return;
    }

    synchronized (this) {
      recipientWallet.add(giverWallet.drop(value));
    }

    // logging
    ctx.logQueue.add(
      new TransactionLogUnit(
        giverWallet, oldGiverWalletVal, recipientWallet, oldRecipientWalletVal, value, reason));
  }
}
