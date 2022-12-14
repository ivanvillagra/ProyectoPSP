package Bdd;

import java.sql.Connection;
import java.sql.DriverManager;

public class BdKutxaBank {

    private static String UP = "knabaxtuk";

    public static Connection Conection(){
        try {

            return DriverManager.getConnection("jdbc:mysql://localhost:3306/kutxabank", UP, UP);


        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}
