package Front;

import Client.MultithreadedClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MaXxxiiiiiiiiiiiaR on 11/28/2017.
 */
public class UiChat extends JFrame {
    private JButton button1;
    private JTextField textField1;
    private JTextArea textArea1;
    private JPanel davoodPanel;
    private JLabel chatting;
    private MultithreadedClient client;

    public UiChat(String myName, String yourName, MultithreadedClient client) {

        this.client = client;
        this.setTitle(myName);
        this.setSize(500,600);
        this.setLocation(700,450);

        this.setContentPane(davoodPanel);

        textArea1.setEditable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        chatting.setText(yourName);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pmProtocol = "pm#" + myName + "#" + yourName + "#" + textField1.getText();
                client.sendStringToServer(pmProtocol);
                String a = "Me : " + textField1.getText();
                textArea1.append(a);
                textArea1.append("\n");
                textField1.setText("");
            }
        });

        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    if ( client.getPm()!=null) {
                        textArea1.append(client.getPm());
                        textArea1.append("\n");
                        client.setPm(null);
                    }else {
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }; thread.start();

        this.setVisible(true);

    }
}
