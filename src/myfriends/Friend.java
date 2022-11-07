package myfriends;

public class Friend {

    // Attributes ----------------------------------------------
    private String name;
    private String phone;
    private String email;

    // Constructor ---------------------------------------------
    public Friend(String name, String phoneNumber, String email) {
        setName(name);
        setPhone(phoneNumber);
        setEmail(email);
    }

    // Getters -------------------------------------------------
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters ---------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // To String --------------------------------------------------------

    @Override
    public String toString() {
        return "Friend name: " + name + " - Phone number: " + phone + " - Email: " + email;
    }
}
