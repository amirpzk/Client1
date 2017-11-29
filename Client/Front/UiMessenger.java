package Front;

import Client.MultithreadedClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

/**
 * Created by amirpez on 11/16/17.
 */
public class UiMessenger extends JFrame{

    private MultithreadedClient threadedClient;
    private java.util.List<String> allUsersList = new ArrayList<>();
    private List<UsersPanel> usersPanels ;
    private JButton logOutButton ;
    JFrame frame ;

    public UiMessenger(MultithreadedClient threadedClient) throws HeadlessException {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame = this ;
        this.setLocation(750,200);
        this.setBackground(new Color(80,61,232));
        this.setTitle(threadedClient.getUser().getUsername());
        logOutButton = new JButton("LogOut");
        usersPanels = new ArrayList<>();
        this.setLayout(new FlowLayout());
        this.setSize(468, 780);
        this.threadedClient = threadedClient;
        this.allUsersList = threadedClient.getAllUsers();
        setOnlinePanels();
        this.add(logOutButton);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usersName = threadedClient.getUser().getUsername() ;
                String logOutProt = "LogOut#"+usersName;
                threadedClient.sendStringToServer(logOutProt);
                System.exit(0);
            }
        });
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (threadedClient.isOnlineListUpdated() == false) {
                        updateUsersPanel();
                        threadedClient.setOnlineListUpdated(true);
                    } else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }; thread.start();
        this.setVisible(true);

//        this.onlinePanels = threadedClient.makeAndGeOnlinePanels();
//        setOnlinePanels();
    }

    public void updateUsersPanel(){
        for (int i=0 ; i<usersPanels.size() ; i++){
            String[] words = usersPanels.get(i).getUsername().getText().split("-");
            for ( int j=0 ; j<threadedClient.getOnlineUsers().size() ; j++ ){
                System.out.println(threadedClient.getOnlineUsers().get(j));
                if ( words[0].equalsIgnoreCase(threadedClient.getOnlineUsers().get(j))){
                    usersPanels.get(i).setUsername(words[0]+"-"+"Online");
                    break;
                }
                usersPanels.get(i).setUsername(words[0]+"-"+"Offline");
            }
        }
    }


    public void setOnlinePanels() {
        for (int i = 0; i < allUsersList.size(); i++) {
            boolean isThisUserOnline = false;
            String thisUser = allUsersList.get(i);
            for (int j = 0; j < threadedClient.getOnlineUsers().size(); j++) {
                if (thisUser.equalsIgnoreCase(threadedClient.getOnlineUsers().get(j))) {
                    isThisUserOnline = true;
                    break;
                }
            }
            String isThisUserOnlineStr;
            if (isThisUserOnline == false) {
                isThisUserOnlineStr = "Offline";
            } else {
                isThisUserOnlineStr = "Online";
            }
            this.add(getOnlinePanel(allUsersList.get(i) +"-"+ isThisUserOnlineStr));
        }
    }

        public JPanel getOnlinePanel(String user) {

            UsersPanel usersPanel = new UsersPanel(user);
            usersPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String[] userSplited = user.split("-");
                    UiChat chatUi = new UiChat(threadedClient.getUser().getUsername(), userSplited[0], threadedClient);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            usersPanels.add(usersPanel);
            return usersPanel;
        }

}
