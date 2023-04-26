package Relations.LoanApplication;

public class LoanApplication {
    private int id;
    private int applicant_id;
    private int amount;
    private String reason;
    private boolean status;

    public LoanApplication() {
    }

    public LoanApplication(int applicant_id, int amount, String reason, boolean status) {
        this.applicant_id = applicant_id;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicantId() {
        return applicant_id;
    }

    public void setApplicantId(int applicant_id) {
        this.applicant_id = applicant_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
