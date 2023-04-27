package Relations.CreditScore;

public class CreditScore {
    private int id;
    private int score;
    private int salary;
    private int net_worth;
    private int total_loans;
    private int repaid_loans;
    private String occupation;

    public CreditScore() {
    }

    public CreditScore(int id, int score, int salary, int net_worth, int total_loans, int repaid_loans,
            String occupation) {
        this.id = id;
        this.score = score;
        this.salary = salary;
        this.net_worth = net_worth;
        this.total_loans = total_loans;
        this.repaid_loans = repaid_loans;
        this.occupation = occupation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getNetWorth() {
        return net_worth;
    }

    public void setNetWorth(int net_worth) {
        this.net_worth = net_worth;
    }

    public int getTotalLoans() {
        return total_loans;
    }

    public void setTotalLoans(int total_loans) {
        this.total_loans = total_loans;
    }

    public int getRepaidLoans() {
        return repaid_loans;
    }

    public void setRepaidLoans(int repaid_loans) {
        this.repaid_loans = repaid_loans;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

}
