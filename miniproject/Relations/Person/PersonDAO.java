package Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PersonDAO {
    // Dao for person class here

    Connection dbConnection;

    public PersonDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    // done
    public Person getPersonById(int person_id) {
        Person res = new Person(null, null, null, null);
        String sql;
        sql = "SELECT name, address, email, password FROM person WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, person_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(person_id);
                res.setName(rs.getString("name"));
                res.setAddress(rs.getString("address"));
                res.setEmail(rs.getString("email"));
                res.setPassword(rs.getString("password"));
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
    public Person getPersonByEmail(String email) {
        Person res = new Person(null, null, null, null);
        String sql;
        sql = "SELECT id, name, address, password FROM person WHERE email = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(rs.getInt("id"));
                res.setName(rs.getString("name"));
                res.setAddress(rs.getString("address"));
                res.setEmail(email);
                res.setPassword(rs.getString("password"));
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
    public Person makePerson(String name, String address, String email, String password) {
        Person res = new Person(name, address, email, password);
        String sql;
        sql = "INSERT INTO person(name, address, email, password) values(?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
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
