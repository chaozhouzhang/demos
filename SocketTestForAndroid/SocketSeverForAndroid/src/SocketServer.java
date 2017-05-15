import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangchaozhou on 17/5/3.
 */
public class SocketServer {
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {

        try {
            System.out.println("Server：Connecting...");
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server：Receiving...");
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String msg = in.readLine();
                    System.out.println("Server：Received：" + msg);
                } catch (Exception e) {
                    System.err.println("Server：Error：" + e.getMessage());
                } finally {
                    clientSocket.close();
                    System.out.println("Server：Close.");
                }
            }
        } catch (IOException e) {
            System.err.println("Server：Error：" + e.getMessage());
        }
    }

}
