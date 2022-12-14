package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6868);
        while (true) {
            Socket socket = serverSocket.accept();
            Thread th = new threadPetitions(socket);
            th.start();

        }
    }
}
