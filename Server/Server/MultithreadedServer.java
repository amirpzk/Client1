package Server;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by amirpez on 11/11/17.
 */
public class MultithreadedServer extends Thread implements ImpMultithreadedServer{
    private Socket clientSocket;
    private Server server;
    private BufferedReader dis;
    private PrintWriter dos;
    private boolean shouldRun = true;
    private Controller controller = new Controller(server);
    private Domain.User user;

    public MultithreadedServer(Server server , Socket socket){
        super("ServerConnectionThread");
        this.clientSocket = socket;
        this.server = server;
    }


    public void sendStringToClient(String msg){
        try
        {
            dos.write(msg);
            dos.flush();
        }
        catch (Exception e)
        {
            System.out.println("PROBLEM <><><><> MultiThreadedServer >>>>> sentStringToClient");
        }
    }

    public void run() {
        try {
            dos = new PrintWriter(clientSocket.getOutputStream(),true) ;
            dis = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sendStringToClient(allUsersProtocol());
//            sendStringToClient(server.onlineUsersListProtocol());

            while(shouldRun) {
//                String line ;
//                while((line=dis.readLine()) == null) {
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

//                String textIn = dis.readUTF();
//                sendOnlineUsersToAllClients(textIn);
                getFromClient();
            }


//            dis.close();
//            dos.close();
//            clientSocket.close();

        }
        catch (IOException e)
        {
            System.out.println("ERROR <><><><><>< MultiThreadedServer >>> run()");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void getFromClient() {
        try {
            controller.identifiear(dis.readLine(), server);
        } catch (IOException e) {
            System.out.println("Problem , MultiThreadedServer >>> getFromClient");
            e.printStackTrace();
        }

    }


    public String allUsersProtocol(){
        List<String> users = server.getServerDao().getListOfFile();
        String usersProt = "users" ;
        for ( int i=0 ; i<users.size() ; i++){
            usersProt += "#"+ users.get(i);
        }
        return usersProt;
    }

    @Override
    public void logOut() {
        try {
            dis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

