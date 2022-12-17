package Server;

import Clases.User;

import java.io.*;
import java.net.Socket;
import Bdd.BdKutxaBank;
public class threadPetitions extends Thread{

   private Socket client;

   public threadPetitions(Socket client) throws IOException {
       this.client = client;


   }

   @Override
    public void run() {
       try {

           ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());

           ObjectInputStream is = new ObjectInputStream(client.getInputStream());



           String petition = (String) is.readObject();

           System.out.println(petition);
           switch (petition) {
               //registro
               case "register" :
                   User newUser = (User) is.readObject();
                   Registro(newUser, os);
                   break;

               //login
               case "login" :
                   break;
           }

       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
   }

   private static void Registro(User newUser,ObjectOutputStream os) throws IOException {

       if (BdKutxaBank.InsertUser(newUser)){
            os.writeObject(true);
       }else {
            os.writeObject(false);
       }

   }

}
