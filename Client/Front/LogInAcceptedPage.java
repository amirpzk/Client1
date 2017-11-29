package Front;

import Client.Client;
import Client.MultithreadedClient;

import javax.swing.*;

/**
 * Created by amirpez on 11/15/17.
 */
public class LogInAcceptedPage extends JFrame {
    private JPanel panel1;
    private JLabel userName;

    public LogInAcceptedPage(MultithreadedClient client) {
        this.setContentPane(panel1);
        this.setSize(300,300);
        this.setVisible(true);
    }
}
