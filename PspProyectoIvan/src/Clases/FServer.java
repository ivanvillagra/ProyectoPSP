package Clases;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;

public class FServer {

    public static void fServer(ObjectOutputStream output) {
        KeyPairGenerator keysGenerador = null;
        try {
            keysGenerador = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        KeyPair parKeys = keysGenerador.generateKeyPair();
        PrivateKey clavePrivada = parKeys.getPrivate();
        PublicKey clavePublica = parKeys.getPublic();

        try {
            output.writeObject(clavePublica);
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mensaje = "Acepta las politicas de empresa de kutxabank";
        try {
            output.writeObject(mensaje);
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Signature firma = null;
        byte[] firmaBytes = null;
        try {
            firma = Signature.getInstance("SHA1WITHRSA");
            firma.initSign(clavePrivada);
            firma.update(mensaje.getBytes());
            firmaBytes = firma.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
        try {
            output.writeObject(firmaBytes);
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
