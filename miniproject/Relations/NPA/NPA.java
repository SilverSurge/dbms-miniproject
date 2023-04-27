package NPA;

public class NPA {
    private int id;
    private int loan_id;
    private int loss;
    private String solution;

    public NPA(int loan_id, int loss, String solution) {
        this.loan_id = loan_id;
        this.loss = loss;
        this.solution = solution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoanId() {
        return loan_id;
    }

    public void setLoanId(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}
