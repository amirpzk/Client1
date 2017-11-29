package Server;

import java.io.Serializable;

/**
 * Created by MaXxxiiiiiiiiiiiaR on 11/27/2017.
 */
public class User implements Serializable {
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
