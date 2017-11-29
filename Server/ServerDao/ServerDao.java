package ServerDao;

import Domain.User;
import Server.MultithreadedServer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirpez on 11/11/17.
 */
public class ServerDao {
    private static HashMap<String, MultithreadedServer> usersAndConnections = new HashMap<>();

    public List<String> getListOfFile(){
        List<String> nameOfUsers = new ArrayList<>();
        File folder = new File("/Users/amirpez/Desktop/Messenger/");
        File[] listOfFiles = folder.listFiles();
        for ( int i=0 ; i<listOfFiles.length ; i++){
            FileInputStream f = null;
            try {
                f = new FileInputStream(listOfFiles[i]);
                ObjectInputStream oi = new ObjectInputStream(f);
                nameOfUsers.add(((User)oi.readObject()).getUsername());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return nameOfUsers ;
    }

    public void storeInFile(User user){
        File file = new File("/Users/amirpez/Desktop/Messenger/"+user.getUsername()+".ser");
        System.out.println("Davood");

        try
        {
            FileOutputStream f = new FileOutputStream(file);
            System.out.println("00000");
            ObjectOutputStream os = new ObjectOutputStream(f);
            System.out.println("111111");
            os.writeObject(user);
            System.out.println("222222");
        }
        catch (Exception e)
        {
            System.out.println("PROBLEM <> ServerDao >> storeInFile");
        }

    }

    public Object readOnFile(String username){
        try
        {
            FileInputStream f = new FileInputStream("/Users/amirpez/Desktop/Messenger/"+username+".ser");
            ObjectInputStream oi = new ObjectInputStream(f);
            return oi.readObject();
        }
        catch (Exception e)
        {
            System.out.println("PROBLEM <><><> ServerDao >>> readOnFile");
            return null;
        }
    }

    public HashMap<String,MultithreadedServer> getUsersAndConnections(){
        return usersAndConnections;
    }
}
