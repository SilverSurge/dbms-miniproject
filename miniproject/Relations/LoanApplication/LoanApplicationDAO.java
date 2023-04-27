package LoanApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

public class LoanApplicationDAO {
    // Dao for person class here

    Connection dbConnection;

    public LoanApplicationDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    // done
    public LoanApplication getLoanApplicationById(int loan_application_id) {

        LoanApplication res = new LoanApplication();
        String sql;
        sql = "SELECT id, applicant_id, amount, reason, status FROM loan_application WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, loan_application_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(rs.getInt("id"));
                res.setApplicantId(rs.getInt("applicant_id"));
                res.setAmount(rs.getInt("amount"));
                res.setReason(rs.getString("reason"));
                res.setStatus(rs.getString("status"));
                return res;
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    // done
    public ArrayList<LoanApplication> getLoanApplicationsByApplicantId(int person_id) {
        ArrayList<LoanApplication> res = new ArrayList<>();
        String sql;
        sql = "SELECT id, applicant_id, amount, reason, status FROM loan_application WHERE applicant_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, person_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LoanApplication loan_app = new LoanApplication();
                loan_app.setId(rs.getInt("id"));
                loan_app.setApplicantId(rs.getInt("applicant_id"));
                loan_app.setAmount(rs.getInt("amount"));
                loan_app.setReason(rs.getString("reason"));
                loan_app.setStatus(rs.getString("status"));
                res.add(loan_app);
            }
            if (res.size() == 0)
                return null;
            return res;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    // done
    public LoanApplication makeLoanApplication(int applicant_id,
            int amount,
            String reason,
            String status) {

        LoanApplication res = new LoanApplication(applicant_id, amount, reason, status);
        String sql;
        sql = "INSERT INTO loan_application(applicant_id, amount, reason, status) values(?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, applicant_id);
            pstmt.setInt(2, amount);
            pstmt.setString(3, reason);
            pstmt.setString(4, status);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int key = keys.getInt(1);
                res.setId(key);
                return res;
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
}
// TODO: function to approve the loan
