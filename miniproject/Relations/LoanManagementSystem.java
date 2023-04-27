
import java.util.Scanner;

import DAO_factory.DAO_Factory;
import Person.Person;
import Person.PersonDAO;
import Transaction.TransactionDAO;

public class LoanManagementSystem {
    private static DAO_Factory factory=new DAO_Factory();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean loggedIn = false;
    private static Person currentUser;

    public static void main(String[] args) throws Exception {
        factory.activateConnection();
        while (true) {
            if (!loggedIn) {
                login();
            } else {
                if (currentUser.getName().equals("user")) {
                    userMenu();
                } else if (currentUser.equals("admin")) {
                    adminMenu();
                }
            }
        }
    }

    public static void login() throws Exception {
        System.out.println("Welcome to the Loan Management System!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("Please enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character after nextInt()

        switch (choice) {
            case 1:
                System.out.println("Please enter your username: ");
                String username = scanner.nextLine();
                System.out.println("Please enter your password: ");
                String password = scanner.nextLine();
                Person tempcheck=factory.getPersonDAO().getPersonByEmail(username);

                if (!username.equals("admin") && password.equals(tempcheck.getPassword())) {
                    System.out.println("Login successful!");
                    currentUser = tempcheck;
                    loggedIn = true;
                } else if (username.equals("admin") && password.equals("password")) {
                    System.out.println("Login successful!");
                    currentUser = tempcheck;
                    loggedIn = true;
                } else {
                    System.out.println("Incorrect username or password.");
                }
                break;
            case 2:
                registerUser();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void registerUser() throws Exception {
        System.out.println("Please enter a name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter a address: ");
        String address = scanner.nextLine();
        System.out.println("Please enter a username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter a password: ");
        String password = scanner.nextLine();

        try {
            factory.activateConnection();
            PersonDAO pDao=factory.getPersonDAO();
            Person p=pDao.makePerson(name, address, username, password);
            System.out.println("Your person ID alloted to you is: "+p.getId());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Person p=factory.getPersonDAO().getPersonByEmail(username);
            System.out.println("You are already registered by the person ID:"+p.getId());
            return ;
        }


        // Store the new user account in a database or file
        // For demonstration purposes, we'll assume the user account was successfully registered
        System.out.println("User account successfully registered.");
    }

    public static void userMenu() throws Exception {
        while (true) {
            System.out.println("Welcome, user!");
            System.out.println("Please select an option:");
            System.out.println("1. Apply for a loan");
            System.out.println("2. Check loan status");
            System.out.println("3. Make a loan transaction");
            System.out.println("4. Check loan eligibility");
            System.out.println("5. Logout");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    applyForLoan();
                    break;
                case 2:
                    checkLoanStatus();
                    break;
                case 3:
                    makeLoanTransaction();
                    break;
                case 4:
                    checkLoanEligibility();
                    break;
                case 5:
                    System.out.println("Logged out.");
                    currentUser = null;
                    loggedIn = false;
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void applyForLoan() throws Exception {
        System.out.println("Please enter the amount of loan you wish to apply for: ");
        Integer amount = scanner.nextInt();

        Integer creditScore = getCreditScore(); // Function to get user's credit score

        if (creditScore >= 700) { // Loan eligibility criteria
            System.out.println("Loan application sent for processing.");
        } else {
            System.out.println("You are not eligible for a loan at this time.");
        }
    }

    public static void checkLoanStatus() throws Exception {
        System.out.println("Please enter your loan application ID: ");
        Integer loanID = scanner.nextInt();

        String status = getLoanStatus(loanID); // Function to get loan application status
        System.out.println("Your loan application status is: " + status);
    }

    public static void makeLoanTransaction() throws Exception {
        System.out.println("Please enter your loan account number: ");
        Integer accountNumber = scanner.nextInt();

        System.out.println("Please enter the amount you wish to deposit or withdraw: ");
        Integer amount = scanner.nextInt();

        String transactionType = "";
        while (!(transactionType.equals("deposit") || transactionType.equals("withdraw"))) {
            System.out.println("Please enter 'deposit' or 'withdraw': ");
            transactionType = scanner.nextLine().toLowerCase();
        }

        if (transactionType.equals("deposit")) {
            deposit(accountNumber, amount); // Function to deposit money into loan account
            System.out.println("Deposit successful.");
        } else if (transactionType.equals("withdraw")) {
            withdraw(accountNumber, amount); // Function to withdraw money from loan account
            System.out.println("Withdrawal successful.");
        }
    }

    public static void checkLoanEligibility() {
        Integer creditScore;
        try {
            creditScore = getCreditScore();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            creditScore=0;
        } // Function to get user's credit score

        if (creditScore >= 7) { // Loan eligibility criteria
            System.out.println("Congratulations! You are eligible for a loan.");
        } else {
            System.out.println("Sorry, you are not eligible for a loan at this time.");
        }
    }

    public static void checkNPAStatus() {
        System.out.println("Please enter the loan account number: ");
        String accountNumber = scanner.nextLine();

        boolean isNPA = checkNPA(accountNumber); // Function to check if loan is an NPA

        if (isNPA) {
            System.out.println("Loan account " + accountNumber + " is an NPA.");
        } else {
            System.out.println("Loan account " + accountNumber + " is not an NPA.");
        }
    }

    public static void resolveNPA() {
        System.out.println("Please enter the loan account number: ");
        String accountNumber = scanner.nextLine();

        boolean isNPA = checkNPA(accountNumber); // Function to check if loan is an NPA

        if (isNPA) {
            System.out.println("Please enter the amount to be paid to resolve the NPA: ");
            Integer amount = Integer.parseInteger(scanner.nextLine());

            boolean resolved = resolveNPA(accountNumber, amount); // Function to resolve NPA

            if (resolved) {
                System.out.println("NPA for loan account " + accountNumber + " has been resolved.");
            } else {
                System.out.println("Unable to resolve NPA for loan account " + accountNumber + ".");
            }
        } else {
            System.out.println("Loan account " + accountNumber + " is not an NPA.");
        }
    }

    // Functions below are not implemented and need to be completed

    public static Integer getCreditScore() throws Exception {
        // Function to get user's credit score
        return factory.getCreditScoreDAO().getCreditScoreById(currentUser.getId()).getScore();

    }

    public static String getLoanStatus(Integer loanappID) throws Exception {
        // Function to get loan application status
        return factory.getLoanApplicationDAO().getLoanApplicationById(loanappID).getStatus();
    }

    public static void deposit(Integer accountNumber, Integer amount) throws Exception {
        TransactionDAO t=factory.getTransactionDAO();
        Integer id= t.makeTransaction(0, accountNumber, amount,"Deposit" , -1);
        System.out.println("Trnsaction Successful id:"+id.toString());
        // Function to deposit money into loan account
    }

    public static void withdraw(Integer accountNumber, Integer amount) throws Exception {
        // Function to withdraw money from loan account
        TransactionDAO t=factory.getTransactionDAO();
        Integer id= t.makeTransaction( accountNumber,0, amount,"withdrawal" , -1);
        System.out.println("Trnsaction Successful id:"+id.toString());
    }

    public static boolean checkNPA(String accountNumber) {
        // Function to check if loan is an NPA
        return false;
    }

    public static boolean resolveNPA(String accountNumber, Integer amount) {
        // Function to resolve N
// Function to resolve NPA
return false;
}

public static void adminLogin() {
    System.out.println("Please enter your admin username: ");
    String username = scanner.nextLine();

    System.out.println("Please enter your admin password: ");
    String password = scanner.nextLine();

    if (validateAdminLogin(username, password)) { // Function to validate admin login credentials
        adminMenu();
    } else {
        System.out.println("Invalid admin username or password.");
    }
}


public static void viewLoanApplications() {
    // Function to view loan applications
}

public static void approveOrRejectLoanApplication() {
    // Function to approve or reject loan application
}

public static boolean validateAdminLogin(String username, String password) {
    // Function to validate admin login credentials
    return false;
}

public static void adminMenu() {
    int choice = 0;

    while (choice != 5) {
        System.out.println("Please select an option:");
        System.out.println("1. Check NPA status");
        System.out.println("2. Resolve NPA");
        System.out.println("3. View loan applications");
        System.out.println("4. Approve or reject loan application");
        System.out.println("5. Exit");

        choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                checkNPAStatus();
                break;
            case 2:
                resolveNPA();
                break;
            case 3:
                viewLoanApplications();
                break;
            case 4:
                approveOrRejectLoanApplication();
                break;
            case 5:
                System.out.println("Exiting admin menu.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}


}