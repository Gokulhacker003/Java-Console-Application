public class Transaction {
    private String id;
    private String type;
    private long amount;
    private long balance;
    public Transaction(String tr_uiD, String tr_type,long tr_amount,long tr_balance){
        this.id=tr_uiD;
        this.type=tr_type;
        this.amount=tr_amount;
        this.balance=tr_balance;
    }

    @Override
    public String toString() {
        return String.format("ID:%s, Type:%s, Amount:%d, Balance:%d",id,type,amount,balance);
    }
}
