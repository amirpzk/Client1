package Server;

/**
 * Created by MaXxxiiiiiiiiiiiaR on 11/27/2017.
 */
public interface ImpMultithreadedServer {

    public void sendStringToClient(String msg);

    public void getFromClient();

    public String allUsersProtocol();

    public void logOut();
}
