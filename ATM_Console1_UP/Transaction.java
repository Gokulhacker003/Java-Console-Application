public class Transaction {
    private String id;       // Transaction User ID
    private String type;     // Type of transaction (e.g., deposit or withdrawal)
    private long amount;     // Amount of the transaction
    private long balance;    // Balance after the transaction

    // Constructor to set up a transaction with ID, type, amount, and balance
    public Transaction(String tr_uiD, String tr_type, long tr_amount, long tr_balance) {
        this.id = tr_uiD;      // Set transaction ID
        this.type = tr_type;   // Set transaction type
        this.amount = tr_amount; // Set transaction amount
        this.balance = tr_balance; // Set balance after transaction
    }

    // Returns transaction details as a string
    @Override
    public String toString() {
        return String.format("ID:%s, Type:%s, Amount:%d, Balance:%d", id, type, amount, balance);
    }
}
