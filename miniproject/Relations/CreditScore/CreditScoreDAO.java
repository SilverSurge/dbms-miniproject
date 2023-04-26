package Relations.CreditScore;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreditScoreDAO {
    // Dao for person class here

    Connection dbConnection;

    public CreditScoreDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    // done
    public CreditScore getCreditScoreById(int cs_id) {
        CreditScore res = new CreditScore();
        String sql;
        sql = "SELECT score, salary, net_worth, total_loans, repaid_loans, occupation FROM credit_score WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, cs_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(cs_id);
                res.setScore(rs.getInt("score"));
                res.setSalary(rs.getInt("salary"));
                res.setNetWorth(rs.getInt("net_worth"));
                res.setTotalLoans(rs.getInt("total_loans"));
                res.setRepaidLoans(rs.getInt("repaid_loans"));
                res.setOccupation(rs.getString("occupation"));
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
    public CreditScore makeCreditScore(int id, int score, int salary, int net_worth, int total_loans, int repaid_loans,
            String occupation) {

        CreditScore res = new CreditScore(id, score, salary, net_worth, total_loans, repaid_loans, occupation);
        String sql;
        sql = "INSERT INTO credit_score(id, score, salary, net_worth, total_loans, repaid_loans, occupation) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, score);
            pstmt.setInt(3, salary);
            pstmt.setInt(4, net_worth);
            pstmt.setInt(5, total_loans);
            pstmt.setInt(6, repaid_loans);
            pstmt.setString(7, occupation);
            pstmt.executeUpdate();
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
    public CreditScore updateCreditScore(int id, int score, int salary, int net_worth, int total_loans,
            int repaid_loans,
            String occupation) {

        CreditScore res = new CreditScore(id, score, salary, net_worth, total_loans, repaid_loans, occupation);
        String sql;
        sql = "UPDATE TABLE credit_score SET score=?, salary=?, net_worth=?, total_loans=?, repaid_loans=?, occupation=?) WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, score);
            pstmt.setInt(2, salary);
            pstmt.setInt(3, net_worth);
            pstmt.setInt(4, total_loans);
            pstmt.setInt(5, repaid_loans);
            pstmt.setString(6, occupation);
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
            return res;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
}
