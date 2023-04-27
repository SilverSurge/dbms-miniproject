package DAO_factory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Account.AccountDAO;
import CreditScore.CreditScoreDAO;
import Loan.LoanDAO;
import LoanApplication.LoanApplicationDAO;
import Person.PersonDAO;
import Transaction.Transaction;
import Transaction.TransactionDAO;

public class DAO_Factory {

    public enum TXN_STATUS {
        COMMIT, ROLLBACK
    };

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sample";
    static final String USER = "root";
    static final String PASS = "";

    Connection dbconnection = null;

    // DAOs
    AccountDAO accountDAO = null;
    CreditScoreDAO creditScoreDAO = null;
    LoanApplicationDAO loanApplicationDAO = null;
    LoanDAO loanDAO = null;
    PersonDAO personDAO = null;
    TransactionDAO transactionDAO = null;


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
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbconnection = DriverManager.getConnection(DB_URL, USER, PASS);
            dbconnection.setAutoCommit(false);
            System.out.println("success: able to load driver class!");

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
    public AccountDAO getAccountDAO() throws Exception {
        if (activeConnection == false)
            throw new Exception("Connection not activated...");

        if (accountDAO == null)
            accountDAO = new AccountDAO(dbconnection);

        return accountDAO;
    }
    public CreditScoreDAO getCreditScoreDAO() throws Exception {
        if (activeConnection == false)
            throw new Exception("Connection not activated...");

        if (creditScoreDAO == null)
            creditScoreDAO = new CreditScoreDAO(dbconnection);

        return creditScoreDAO;
    }
    public LoanApplicationDAO getLoanApplicationDAO() throws Exception {
        if (activeConnection == false)
            throw new Exception("Connection not activated...");

        if (loanApplicationDAO == null)
            loanApplicationDAO = new LoanApplicationDAO(dbconnection);

        return loanApplicationDAO;
    }
    public LoanDAO getLoanDAO() throws Exception {
        if (activeConnection == false)
            throw new Exception("Connection not activated...");

        if (loanDAO == null)
            loanDAO = new LoanDAO(dbconnection);

        return loanDAO;
    }
    public TransactionDAO getTransactionDAO() throws Exception {
        if (activeConnection == false)
            throw new Exception("Connection not activated...");

        if (transactionDAO == null)
            transactionDAO = new TransactionDAO(dbconnection);

        return transactionDAO;
    }

    public PersonDAO getPersonDAO() throws Exception {
        if (activeConnection == false)
            throw new Exception("Connection not activated...");

        if (personDAO == null)
            personDAO = new PersonDAO(dbconnection);

        return personDAO;
    }
}