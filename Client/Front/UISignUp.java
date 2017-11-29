package Front;

import Client.Client;
import Client.MultithreadedClient;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by amirpez on 11/11/17.
 */
public class UISignUp extends JFrame {
        private JFrame frame;
        private JTextField tfUsername;
        private JTextField tfName;
        private JButton btnSubmit;
        private JButton btnLogin;
        private Client c1;

        public UISignUp(Client c) {
            this.c1 = c;
            this.frame = new JFrame();
            this.frame.setSize(300, 100);
            this.frame.setTitle("UiRegister");
            this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.frame.setLayout(new FlowLayout());

            this.tfUsername = new JTextField("User");
            this.tfName = new JTextField("pass");
            this.btnSubmit = new JButton("Submit");
            this.btnLogin = new JButton("login");

            this.tfUsername.setPreferredSize(new Dimension(100, 30));
            this.tfName.setPreferredSize(new Dimension(100, 30));

            this.frame.add(tfUsername);
            this.frame.add(tfName);
            this.frame.add(btnSubmit);
            this.frame.add(btnLogin);

            c1.startClient();
            MultithreadedClient client = c1.getClientThread();

            btnSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    client.sendStringToServer("signup#"+getUsername()+"#"+getName());
                }
            });

            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UiSignin uiSignin = new UiSignin(client);
                }
            });

            this.frame.setVisible(true);
        }

        public String getUsername() {
            return tfUsername.getText();
        }

        public String getname() {
            return tfName.getText();
        }
    }


