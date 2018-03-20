package solaris.org.solariswallet.ui.wallet_activity;

import org.solarisj.core.Coin;
import org.solarisj.core.Sha256Hash;
import org.solarisj.core.Transaction;

import java.io.Serializable;
import java.util.Map;

import solaris.org.solariswallet.contacts.AddressLabel;

/**
 * Created by ming on 2018.3.19.
 */
public class TransactionWrapper implements Serializable{

    public static enum TransactionUse{
        SENT_SINGLE,
        RECEIVE,
        STAKE
        ;

    }

    private transient Transaction transaction;
    private Sha256Hash txId;
    /** Map of Address labels ordered by output position */
    private Map<Integer,AddressLabel> outputLabels;
    private Map<Integer,AddressLabel> inputsLabels;
    private Coin amount;
    private TransactionUse transactionUse;


    public TransactionWrapper(Transaction transaction, Map<Integer,AddressLabel> inputsLabels, Map<Integer,AddressLabel> outputLabels, Coin amount, TransactionUse transactionUse) {
        this.transaction = transaction;
        this.txId = transaction.getHash();
        this.inputsLabels = inputsLabels;
        this.outputLabels = outputLabels;
        this.amount = amount;
        this.transactionUse = transactionUse;
    }


    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Sha256Hash getTxId() {
        return txId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Coin getAmount() {
        return amount;
    }

    public TransactionUse getTransactionUse() {
        return transactionUse;
    }

    public Map<Integer, AddressLabel> getInputsLabels() {
        return inputsLabels;
    }

    public Map<Integer, AddressLabel> getOutputLabels() {
        return outputLabels;
    }

    public boolean isSent() {
        return transactionUse == TransactionUse.SENT_SINGLE;
    }

    public boolean isStake() {
        return transactionUse == TransactionUse.STAKE;
    }

    @Override
    public String toString() {
        return "TransactionWrapper{" +
                "transaction=" + transaction +
                ", txId=" + txId +
                ", outputLabels=" + outputLabels +
                ", inputsLabels=" + inputsLabels +
                ", amount=" + amount +
                ", transactionUse=" + transactionUse +
                '}';
    }
}
