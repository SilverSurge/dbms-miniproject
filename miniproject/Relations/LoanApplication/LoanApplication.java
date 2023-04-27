package Relations.LoanApplication;

public class LoanApplication {
    private int id;
    private int applicant_id;
    private int amount;
    private String reason;
    private String status;

    public LoanApplication() {
    }

    public LoanApplication(int applicant_id, int amount, String reason, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
