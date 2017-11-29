package Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by MaXxxiiiiiiiiiiiaR on 11/26/2017.
 */
public class UsersPanel extends JPanel {

    JLabel username ;

    public UsersPanel(String user) {
        username = new JLabel(user);
        this.setOpaque(true);
        this.setBackground(new Color(80,61,232));
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(468, 100));
        this.add(username);
        username.setOpaque(true);
        username.setBackground(new Color(80,61,232));
        username.setForeground(new Color(255,255,255));
    }

    public JLabel getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }
}
