package useradmin2;

public class User {

    // Attributes -------------------------------
    private int ID;
    private String name;
    private String password;

    // Constructors -----------------------------
    User(int ID) {
        setID(ID);
    }

    User(int ID, String name, String password) {
        setID(ID);
        setName(name);
        setPassword(password);
    }

    // Getters -----------------------------------
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // Setters ----------------------------------
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Object TO STRING ------------------

    @Override
    public String toString() {
        return "ID: " + ID + " - Username: " + name + " - Password: " + password;
    }
}
