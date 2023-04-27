package Loan;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

public class LoanDAO {
    // Dao for person class here

    Connection dbConnection;

    public LoanDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    // done
    public Loan getLoanById(int loan_id) {

        Loan res = new Loan();
        String sql;
        sql = "SELECT application_id, account_id, rate_of_interest, principal_amount, remaining_amount, remaining_time FROM loan WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, loan_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(loan_id);
                res.setApplicationId(rs.getInt("application_id"));
                res.setAccountId(rs.getInt("account_id"));
                res.setRateOfInterest(rs.getInt("rate_of_interest"));
                res.setPrincipalAmount(rs.getInt("principal_amount"));
                res.setRemainingAmount(rs.getInt("remaining_amount"));
                res.setRemainingTime(rs.getInt("remaining_time"));
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
    public ArrayList<Loan> getLoanByPersonId(int person_id) {

        ArrayList<Loan> res = new ArrayList<>();
        String sql;
        sql = "SELECT loan.id, application_id, account_id, rate_of_interest, principal_amount, remaining_amount, remaining_time FROM loan INNER JOIN account ON account.id = loan.account_id AND account.person_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, person_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Loan loan = new Loan();
                loan.setId(rs.getInt("id"));
                loan.setApplicationId(rs.getInt("application_id"));
                loan.setAccountId(rs.getInt("account_id"));
                loan.setRateOfInterest(rs.getInt("rate_of_interest"));
                loan.setPrincipalAmount(rs.getInt("principal_amount"));
                loan.setRemainingAmount(rs.getInt("remaining_amount"));
                loan.setRemainingTime(rs.getInt("remaining_time"));
                res.add(loan);
            }
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
    public Loan makeLoan(int applicant_id, int account_id, int rate_of_interest, int principal_amount,
            int remaining_amount, int remaining_time) {
        Loan res = new Loan(applicant_id, account_id, rate_of_interest, principal_amount, remaining_amount,
                remaining_time);
        String sql;
        sql = "INSERT INTO loan(applicant_id, account_id, rate_of_interest, principal_amount, remaining_amount, remaining_time) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, applicant_id);
            pstmt.setInt(2, account_id);
            pstmt.setInt(3, rate_of_interest);
            pstmt.setInt(4, principal_amount);
            pstmt.setInt(5, remaining_amount);
            pstmt.setInt(6, remaining_time);
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
