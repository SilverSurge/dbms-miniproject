package Relations.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

public class AccountDAO {
    // Dao for person class here

    Connection dbConnection;

    public AccountDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    // done
    public Account getAccountById(int account_id) {
        Account res = new Account();
        String sql;
        sql = "SELECT person_id, balance FROM account WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, account_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(account_id);
                res.setPersonId(rs.getInt("person_id"));
                res.setBalance(rs.getInt("balance"));
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
    public ArrayList<Account> getAccountsByPersonId(int person_id) {
        ArrayList<Account> res = new ArrayList<>();
        String sql;
        sql = "SELECT id, balance FROM account WHERE person_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, person_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Account acc = new Account(-1, -1);
                acc.setId(rs.getInt("id"));
                acc.setPersonId(person_id);
                acc.setBalance(rs.getInt("balance"));
                res.add(acc);
            }
            if (res.size() > 0)
                return res;
            return null;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    // done
    public Account makeAccount(int person_id, int balance) {
        Account res = new Account(person_id, balance);
        String sql;
        sql = "INSERT INTO account(person_id, balance) VALUES(?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, person_id);
            pstmt.setInt(2, balance);
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
