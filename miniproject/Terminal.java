import java.util.*;
import Relations.DAO_Factory;
import Relations.Person.PersonDAO;

public class Terminal {

    DAO_Factory dao_Factory;

    public static void main(String[] args) {
        DAO_Factory dao_factory = new DAO_Factory();
        System.out.println("The followings is a mapping of functions");
        System.out.println("1 : register");

        Scanner scanner = new Scanner(System.in);
        int option;
        while (true) {
            option = scanner.nextInt();
            if (option == -1)
                break;
            switch (option) {
                case 1: {

                    break;
                }

                default: {

                    System.out.println("invalid input");
                    break;
                }
            }

        }
        scanner.close();

    }

    void register(Scanner scanner, DAO_Factory dao_factory) {
        try {
            PersonDAO personDAO = dao_factory.getPersonDAO();
            System.out.println("Please enter a name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter a address: ");
            String address = scanner.nextLine();
            System.out.println("Please enter a username: ");
            String username = scanner.nextLine();
            System.out.println("Please enter a password: ");
            String password = scanner.nextLine();
            makePerson(name, address, email, password, false);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}