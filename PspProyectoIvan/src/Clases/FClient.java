package Clases;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.*;

public class FClient {

    public static boolean fClient(ObjectInputStream input) {

        PublicKey clavePublica = null;
        try {
            clavePublica = (PublicKey) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        String mensaje = null;
        try {
            mensaje = (String) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        byte[] firmaBytes = null;
        try {
            firmaBytes = (byte[]) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        Signature firma = null;
        try {
            firma = Signature.getInstance("SHA1WITHRSA");
            firma.initVerify(clavePublica);
            firma.update(mensaje.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boolean respnse = false;
        try {
            respnse = firma.verify(firmaBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return respnse;


    }


}
