package Relations.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class TransactionDAO {
    // Dao for person class here

    Connection dbConnection;

    public TransactionDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    // done
    public Transaction getTransactionById(int transaction_id) {

        Transaction res = new Transaction();
        String sql;
        sql = "SELECT sender_id, receiver_id, amount, context, loan_id FROM person WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, transaction_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(transaction_id);
                res.setSenderId(rs.getInt("sender_id"));
                res.setReceiverId(rs.getInt("receiver_id"));
                res.setAmount(rs.getInt("amount"));
                res.setContext(rs.getBoolean("context"));
                res.setLoanId(rs.getInt("loan_id"));
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
    public ArrayList<Transaction> getTransactionsByPersonId(int person_id) {
        ArrayList<Transaction> res = new ArrayList<>();
        String sql;
        sql = "SELECT id, sender_id, receiver_id, amount, context, loan_id FROM person WHERE sender_id = ? OR receiver_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, person_id);
            pstmt.setInt(2, person_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setId(rs.getInt("id"));
                trans.setSenderId(rs.getInt("sender_id"));
                trans.setReceiverId(rs.getInt("receiver_id"));
                trans.setAmount(rs.getInt("amount"));
                trans.setContext(rs.getBoolean("context"));
                trans.setLoanId(rs.getInt("loan_id"));
                res.add(trans);
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
    public Transaction makeTransaction(int sender_id, int receiver_id, int amount, boolean context, int loan_id) {
        Transaction res = new Transaction(sender_id, receiver_id, amount, context, loan_id);
        String sql;
        sql = "INSERT INTO transaction(sender_id, receiver_id, amount, context, loan_id) values(?,?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, sender_id);
            pstmt.setInt(2, receiver_id);
            pstmt.setInt(3, amount);
            pstmt.setBoolean(4, context);
            pstmt.setInt(5, loan_id);
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
    // we have to handle the update the loan table

    public boolean applyTransaction(Transaction trans) {
        return true;
    }

}
