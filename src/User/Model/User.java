package User.Model;

import java.util.UUID;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String description;

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public String getPassword() { return this.password; }
    public String getDescription() { return this.description; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setDescription(String description) { this.description = description; }

    public User(int id, String name, String email, String password, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public User(String name, String email, String password, String description) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
    }
}
