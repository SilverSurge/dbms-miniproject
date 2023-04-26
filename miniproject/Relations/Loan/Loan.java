package Relations.Loan;

public class Loan {
    private int id;
    private int application_id;
    private int account_id;
    private int rate_of_interest;
    private int principal_amount;
    private int remaining_amount;
    private int remaining_time;

    public Loan() {
    }

    public Loan(int application_id, int account_id, int rate_of_interest, int principal_amount, int remaining_amount,
            int remaining_time) {
        this.application_id = application_id;
        this.account_id = account_id;
        this.rate_of_interest = rate_of_interest;
        this.principal_amount = principal_amount;
        this.remaining_amount = remaining_amount;
        this.remaining_time = remaining_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicationId() {
        return application_id;
    }

    public void setApplicationId(int application_id) {
        this.application_id = application_id;
    }

    public int getAccountId() {
        return account_id;
    }

    public void setAccountId(int account_id) {
        this.account_id = account_id;
    }

    public int getRateOfInterest() {
        return rate_of_interest;
    }

    public void setRateOfInterest(int rate_of_interest) {
        this.rate_of_interest = rate_of_interest;
    }

    public int getPrincipalAmount() {
        return principal_amount;
    }

    public void setPrincipalAmount(int principal_amount) {
        this.principal_amount = principal_amount;
    }

    public int getRemainingAmount() {
        return remaining_amount;
    }

    public void setRemainingAmount(int remaining_amount) {
        this.remaining_amount = remaining_amount;
    }

    public int getRemainingTime() {
        return remaining_time;
    }

    public void setRemainingTime(int remaining_time) {
        this.remaining_time = remaining_time;
    }

}
