package Server;


import Domain.User;
import Server.Server;
import ServerDao.ServerDao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by amirpez on 11/11/17.
 */
public class Controller {
    private Server server ;


    public Controller(Server server) {
        this.server = server ;
    }

    public void identifiear(String str,Server server){
        String[] words= str.split("#");
        if (words[0].equalsIgnoreCase("signup")){
            server.signUp(words[1],words[2]);
        }
        if (words[0].equalsIgnoreCase("signin")){
            server.signIn(words[1]);
        }
        if (words[0].equalsIgnoreCase("pm")){
            String sendToUser = words[2];
            MultithreadedServer threadToSend = server.getServerThreadToSend(sendToUser);
            threadToSend.sendStringToClient("serverPm#"+words[1]+"#"+words[3]);
        }
        if ( words[0].equalsIgnoreCase("logout")){
            MultithreadedServer threadToLogOut = server.getServerThreadToSend(words[1]);
            server.logOut(threadToLogOut);
        }
    }

}
