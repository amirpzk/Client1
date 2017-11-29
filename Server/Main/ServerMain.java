package Main;

import Front.ServerStart;
import Server.Server;
import ServerDao.ServerDao;

/**
 * Created by amirpez on 11/12/17.
 */
public class ServerMain {

    public static void main(String[] args) {
        Server server = new Server();
        ServerStart serverStart = new ServerStart(server);
    }
}
