package app;

public class Customer {
    private String id;
    private String name;
    private String phoneNumber; // Mengganti email dengan phoneNumber

    public Customer() {}
    public Customer(String name, String phoneNumber) { // Mengubah parameter constructor
        this.name = name;
        this.phoneNumber = phoneNumber; // Mengubah parameter constructor
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; } // Mengubah getter
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; } // Mengubah setter
}
