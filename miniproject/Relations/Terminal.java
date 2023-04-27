package Relations;

import java.util.Scanner;

public class Terminal {

    static Scanner scanner = new Scanner(System.in);
    static boolean loggedIn = false;
    static String currentUser = "";

    public static void main(String[] args) {
        while (true) {
            if (!loggedIn) {
                login();
            } else {
                if (currentUser.equals("user")) {
                    userMenu();
                } else if (currentUser.equals("admin")) {
                    adminMenu();
                }
            }
        }
    }

    public static void login() {
        System.out.println("Welcome to the Loan Management System!");
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        if (username.equals("user") && password.equals("password")) {
            System.out.println("Login successful!");
            currentUser = "user";
            loggedIn = true;
        } else if (username.equals("admin") && password.equals("password")) {
            System.out.println("Login successful!");
            currentUser = "admin";
            loggedIn = true;
        } else {
            System.out.println("Incorrect username or password.");
        }
    }

    public static void userMenu() {
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
                    currentUser = "";
                    loggedIn = false;
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void applyForLoan() {
        System.out.println("Please enter the amount of loan you wish to apply for: ");
        double amount = Double.parseDouble(scanner.nextLine());

        double creditScore = getCreditScore(); // Function to get user's credit score

        if (creditScore >= 700) { // Loan eligibility criteria
            System.out.println("Loan application sent for processing.");
        } else {
            System.out.println("You are not eligible for a loan at this time.");
        }
    }

    public static void checkLoanStatus() {
        System.out.println("Please enter your loan application ID: ");
        String loanID = scanner.nextLine();

        String status = getLoanStatus(loanID
        ); // Function to get loan application status
        System.out.println("Your loan application status is: " + status);
    }

    public static void makeLoanTransaction() {
        System.out.println("Please enter your loan account number: ");
        String accountNumber = scanner.nextLine();

        System.out.println("Please enter the amount you wish to deposit or withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());

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
        double creditScore = getCreditScore(); // Function to get user's credit score

        if (creditScore >= 700) { // Loan eligibility criteria
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
            double amount = Double.parseDouble(scanner.nextLine());

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

    public static double getCreditScore() {
        // Function to get user's credit score
        return 0.0;
    }

    public static String getLoanStatus(String loanID) {
        // Function to get loan application status
        return "";
    }

    public static void deposit(String accountNumber, double amount) {
        // Function to deposit money into loan account
    }

    public static void withdraw(String accountNumber, double amount) {
        // Function to withdraw money from loan account
    }

    public static boolean checkNPA(String accountNumber) {
        // Function to check if loan is an NPA
        return false;
    }

    public static boolean resolveNPA(String accountNumber, double amount) {
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

public static boolean validateUserLogin(String username, String password) {
    // Function to validate user login credentials
    return false;
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

public static void viewLoanApplications() {
    // Function to view loan applications
}

public static void approveOrRejectLoanApplication() {
    // Function to approve or reject loan application
}
}