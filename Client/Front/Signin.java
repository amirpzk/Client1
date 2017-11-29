package Front;

import Client.MultithreadedClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by MaXxxiiiiiiiiiiiaR on 11/28/2017.
 */
public class Signin extends JFrame {
    private JButton submitButton;
    private JTextField usernameTextField;
    private JPanel davoodPanel;
    private MultithreadedClient client;
    JFrame frame ;

    public Signin(MultithreadedClient client) {

        frame = this ;
        this.client = client;

        this.setContentPane(davoodPanel);

        this.setSize(450, 350);
        this.setLocation(750,400);
        this.setTitle("UiRegister");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prot = "signin#" + usernameTextField.getText();
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
