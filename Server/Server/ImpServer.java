package Server;

/**
 * Created by amirpez on 11/11/17.
 */
public interface ImpServer {
    void startServer();

    public void signUp(String username,String name);

    public void signIn(String username);

    public void sendOnlineUsersToAllClients();

    public MultithreadedServer getServerThreadToSend(String sendTo);

    public void logOut(MultithreadedServer multithreadedServer);

}
