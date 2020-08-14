package sk.Spedry.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Date;

public class ServerMain {

    static final int PORT = 50000;
    private static InetAddress ip = null;
    private static String hostname = null;
    //private static final Logger logger = LogManager.getLogger(ServerMain.class);
    public static void main(String[] args) throws IOException {
        //BasicConfigurator.configure();
        //logger.debug("test");
        System.out.println("Loading...");

        ServerSocket serverConnect = new ServerSocket(PORT);
        ip = InetAddress.getLocalHost();
        hostname = ip.getHostName();
        System.out.println("IP: " + ip
                + "\nName of host: " + hostname
                + "\nServer is starting..."
                + "\nWaiting for input on port: " + PORT + "...");
        try {
            while(true) {
                Server startServer = new Server(serverConnect.accept());

                System.out.println("Connection established. (" + new Date() + ")");
                System.out.println("Number of active threads from the given thread: " + Thread.activeCount());

                Thread thread = new Thread(startServer);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Error during creating connection: " + e.getMessage());
        }
    }
}