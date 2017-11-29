package Domain;

import java.io.Serializable;

/**
 * Created by amirpez on 11/11/17.
 */
public class User implements Serializable{
    private String username;
    private String name;

    public User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
}
