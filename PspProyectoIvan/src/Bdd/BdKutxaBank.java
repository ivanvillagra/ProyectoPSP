package Bdd;

import Clases.User;
import com.mysql.jdbc.Statement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;

public class BdKutxaBank {

    private static String UP = "knabaxtuk";

    public static Connection conection(){
        try {

            return DriverManager.getConnection("jdbc:mysql://localhost:3306/kutxabank", UP, UP);


        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public static boolean InsertUser(User newUser){
        try{

            Connection bd = conection();

            String query = " insert into users (Name, Surname, pass, DNI)"
                    + " values (?, ?, ?, ?)";

            assert bd != null;
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString (1, newUser.name());
            preparedStmt.setString (2, newUser.surName());



            preparedStmt.setString (3, getMd5(newUser.pass()));
            preparedStmt.setString(4, newUser.dni());


            preparedStmt.execute();
            bd.close();

            createFirstBankAcount();


            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static void  createFirstBankAcount() throws SQLException {
        Connection bd = conection();

        String sql = "SELECT MAX(idUser) from users";
        PreparedStatement preStamenGet = bd.prepareStatement(sql);
        ResultSet  rs = preStamenGet.executeQuery();

        long id  = 0 ;
        while (rs.next()) {
           id = rs.getLong(1);
           break;
        }


        String query = " insert into bankaccounts (iban, balance, idUser)"
                + " values (?, ?, ?)";

        assert bd != null;
        PreparedStatement preparedStmt = bd.prepareStatement(query);
        boolean  t = true;
        String iban = null;
        while (t){
            Random rnd = new Random();
            iban = "ES" + String.format("%06d", rnd.nextInt(999999999));
            t = existIban(iban,bd);
        }

        preparedStmt.setString (1,iban);
        preparedStmt.setDouble (2, 0);
        preparedStmt.setLong (3, id);

      preparedStmt.execute();

      bd.close();

    }

    public static Boolean  existIban(String iban,Connection bd) throws SQLException {
        String query = " SELECT iban FROM bankaccounts WHERE iban = ? ";
        PreparedStatement preparedStmt = bd.prepareStatement(query);
        preparedStmt.setString (1, iban);

        ResultSet rs = preparedStmt.executeQuery();

        if ( rs.getRow() == 0 )
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public static User InicioSesion(){


        return new User("","","","");

    };


        public static String getMd5(String input)
    {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
