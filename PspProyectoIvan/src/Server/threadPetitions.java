package Server;

import Clases.Acount;
import Clases.User;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

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
                   User userLog = (User) is.readObject();
                   Login(userLog, os);
                   break;

               case "getAcounts":
                   User userAcount = (User) is.readObject();
                   System.out.println(userAcount.idUser());
                   getAcountsOfUser(userAcount,os);
                   break;

           }

       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
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

   private static void Login(User user,ObjectOutputStream os) throws IOException, SQLException {
        User  userLogin = BdKutxaBank.InicioSesion(user);
           if (userLogin!=null){
               os.writeObject(userLogin);
           }else {
               os.writeObject(null);
           }

   }

   private static void getAcountsOfUser(User user,ObjectOutputStream os) throws SQLException, IOException {
       List<Acount> listAcounts = BdKutxaBank.acountListOfUser(user);
       if (listAcounts!=null){
           os.writeObject(listAcounts);
       }else {
           os.writeObject(null);
       }
   }

}
