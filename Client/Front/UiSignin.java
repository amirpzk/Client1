package Front;

import Client.Client;
import Client.MultithreadedClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by amirpez on 11/4/17.
 */
public class UiSignin extends JFrame{

    private JTextField tfUsername;
    private JButton btnSubmit;
    private MultithreadedClient client;


    public UiSignin(MultithreadedClient client) {
        this.client = client;

        this.setSize(300, 100);
        this.setTitle("UiRegister");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());


        this.tfUsername = new JTextField("User");
        this.btnSubmit = new JButton("Submit");

        this.tfUsername.setPreferredSize(new Dimension(100, 30));

        this.add(tfUsername);
        this.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String prot = "signin#" + tfUsername.getText();
                client.sendStringToServer(prot);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e1) {
                    System.out.println("SleepTimeError");
                }
                boolean isAccess = client.isLoginAccessBoolean();
                if ( isAccess == true){
                    System.out.println("dadaaaaash");
                    UiMessenger uiMessenger = new UiMessenger(client);
                }
                else
                {
                    LoginError loginError = new LoginError();
                }
            }
        });

        this.setVisible(true);
    }




}