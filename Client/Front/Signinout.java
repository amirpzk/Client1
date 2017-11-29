package Front;

import Client.Client;
import Client.MultithreadedClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by MaXxxiiiiiiiiiiiaR on 11/28/2017.
 */
public class Signinout extends JFrame{
    private JButton signInButton1;
    private JButton signUpButton;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JPanel davoodPanel;
    private Client c1 ;
    private JFrame frame ;

    public Signinout(Client c1) {
        this.c1 = c1;
        frame = this ;
        this.setSize(450, 350);
        this.setLocation(700,400);
        this.setTitle("UiRegister");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(davoodPanel);

        c1.startClient();
        MultithreadedClient client = c1.getClientThread();


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendStringToServer("signup#"+getUsername()+"#"+getName());
            }
        });

        signInButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Signin signin = new Signin(client);
                frame.dispose();
            }
        });

        setVisible(true);
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getname() {
        return nameTextField.getText();
    }

}
