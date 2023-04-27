package Transaction;

public class Transaction {
    private int id;
    private int sender_id;
    private int receiver_id;
    private int amount;
    private String context;
    private int loan_id;

    public Transaction() {
    }

    public Transaction(int sender_id, int receiver_id, int amount, String context, int loan_id) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.amount = amount;
        this.context = context;
        this.loan_id = loan_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return sender_id;
    }

    public void setSenderId(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiverId() {
        return receiver_id;
    }

    public void setReceiverId(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getLoanId() {
        return loan_id;
    }

    public void setLoanId(int loan_id) {
        this.loan_id = loan_id;
    }

    public String toString() {
        return "-----------------------------\n" +
                "Transaction Details\n" +
                "id         : " + getId() + "\n" +
                "sender_id  : " + getSenderId() + "\n" +
                "receiver_id: " + getReceiverId() + "\n" +
                "amount     : " + getAmount() + "\n" +
                "context    : " + getContext() + "\n" +
                "loan_id    : " + getLoanId() + "\n" +
                "-----------------------------\n";
    };
}
