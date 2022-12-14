package Server;

import java.io.*;
import java.net.Socket;

public class threadPetitions extends Thread{

   private Socket client;

   public threadPetitions(Socket client) throws IOException {
       this.client = client;


   }

   @Override
    public void run() {
       try {
           DataInputStream is = new DataInputStream(client.getInputStream());

           DataOutputStream os = new DataOutputStream(client.getOutputStream());

           String petition = is.readUTF();
           System.out.println(petition);

           switch (petition){
               //registro
               case "register" :
                   os.writeUTF("registrao");
                   break;

               //login
               case "login" :
                   os.writeUTF("login");
                   break;
           }

       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }

}
