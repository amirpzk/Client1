package Front;

import javax.swing.*;
import Server.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MaXxxiiiiiiiiiiiaR on 11/29/2017.
 */
public class ServerStart extends JFrame {
    private JPanel panel1;
    private JButton startButton;
    private Server server ;

    public ServerStart(Server server) {

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(200,100);
        this.setLocation(500,500);
        this.server = server ;
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.startServer();
            }
        });
        this.setVisible(true);
    }
}
