package Relations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Relations.Person.PersonDAO;

public class DAO_Factory {

    public enum TXN_STATUS {
        COMMIT, ROLLBACK
    };

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/LMS";
    static final String USER = "root";
    static final String PASS = "its a secret";

    Connection dbconnection = null;

    // DAOs
    PersonDAO personDAO = null;

    boolean activeConnection = false;

    public DAO_Factory() {
        dbconnection = null;
        activeConnection = false;
    }

    public void activateConnection() throws Exception {
        if (activeConnection == true)
            throw new Exception("Connection already active");
        System.out.println("Connecting to database...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbconnection = DriverManager.getConnection(DB_URL, USER, PASS);
            dbconnection.setAutoCommit(false);
            activeConnection = true;
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void deactivateConnection(TXN_STATUS txn_status) {
        // Okay to keep deactivating an already deactivated connection
        activeConnection = false;
        if (dbconnection != null) {
            try {
                if (txn_status == TXN_STATUS.COMMIT)
                    dbconnection.commit();
                else
                    dbconnection.rollback();

                dbconnection.close();
                dbconnection = null;

                // Nullify all DAO objects
                personDAO = null;

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }

    public PersonDAO getStudentDAO() throws Exception {
        if (activeConnection == false)
            throw new Exception("Connection not activated...");

        if (personDAO == null)
            personDAO = new PersonDAO(dbconnection);

        return personDAO;
    }
}