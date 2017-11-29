package Server;

import Domain.User;
import ServerDao.ServerDao;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by amirpez on 11/11/17.
 */
public class Server implements ImpServer {
    private static ServerSocket serverSocket;
    private Socket clientSocket;
    private ServerDao serverDao = new ServerDao();
    private boolean shouldRun = true;
    private final int PORT = 7001;
    private static List<MultithreadedServer> connections = new ArrayList<>();
    private MultithreadedServer serverThread;
    static HashMap<String ,MultithreadedServer> onlineUsersMap = new HashMap<>();

    @Override
    public void startServer(){
        try
        {
            this.serverSocket = new ServerSocket(PORT);
            System.out.println("1");
            while (shouldRun)
            {
                System.out.println("2");
                clientSocket = serverSocket.accept();
                System.out.println("3");
                serverThread = new MultithreadedServer(this ,clientSocket);
                System.out.println("4");
                serverThread.start();
                System.out.println("5");
                connections.add(serverThread);
//                onlineUsersMap.put()
            }
        }
        catch (Exception e)
        {
            System.out.println(
                    "PROBLEM ><><><>< Server.Java >><><><><><><><><><><><>< startServer() ><><><><><><><><><><><><><><><><");
        }
    }

    public ServerSocket getServerSocket(){
        return this.serverSocket;
    }


    public void signUp(String username, String name){
        User user = new User(username,name);
        try
        {
            System.out.println("amir3");
            serverDao.storeInFile(user);
            System.out.println("amir4");
        }
        catch (Exception e)
        {
            System.out.println("PROBLEM <><><> Server.java <><>< signUp ><><>");
        }

    }


    public void signIn(String username){
       if (serverDao.readOnFile(username)!=null){
           User currentUser = (User) serverDao.readOnFile(username);
           onlineUsersMap.put(username,serverThread);
           sendOnlineUsersToAllClients();
           serverThread.sendStringToClient("signin#true#"+currentUser.getUsername()+"#"+currentUser.getName());
           serverThread.allUsersProtocol();
       }
       else
       {
           serverThread.sendStringToClient("signin#false");
       }
    }


    public void sendOnlineUsersToAllClients(){

        String onlineUsersProt = "onlineusers";
        for(Map.Entry<String, MultithreadedServer> entry : onlineUsersMap.entrySet()){
            onlineUsersProt += "#" + entry.getKey();
        }
        System.out.println("3");
        System.out.println(onlineUsersProt);
        for(Map.Entry<String, MultithreadedServer> entry : onlineUsersMap.entrySet()){
            onlineUsersMap.get(entry.getKey()).sendStringToClient(onlineUsersProt);
        }
        System.out.println("4");
    }

    public ServerDao getServerDao() {
        return serverDao;
    }

    public MultithreadedServer getServerThreadToSend(String sendTo){
        MultithreadedServer multithreadedServer = onlineUsersMap.get(sendTo);
        return multithreadedServer;
    }

    @Override
    public void logOut(MultithreadedServer multithreadedServer) {
        System.out.println("0");
        onlineUsersMap.values().remove(multithreadedServer);
        System.out.println("1");
        sendOnlineUsersToAllClients();
    }

    public List<MultithreadedServer> getConnections() {
        return connections;
    }
}
