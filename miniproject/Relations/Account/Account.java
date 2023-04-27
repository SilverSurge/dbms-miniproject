package Account;

public class Account {
    private int id;
    private int person_id;
    private int balance;

    public Account() {
    }

    public Account(int person_id, int balance) {
        this.person_id = person_id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return person_id;
    }

    public void setPersonId(int person_id) {
        this.person_id = person_id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
