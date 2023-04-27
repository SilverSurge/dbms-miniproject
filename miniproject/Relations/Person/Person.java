package Relations.Person;

public class Person {
    private int id;
    private String name;
    private String address;
    private String email;
    private String password;
    private boolean is_admin;

    public Person() {

    }

    public Person(String name, String address, String email, String password, boolean is_admin) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.is_admin = is_admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return is_admin;
    }

    public void setIsAdmin(boolean is_admin) {
        this.is_admin = is_admin;
    }

}
