import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService threadPool;

    public Server(int poolSize) {
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket) {
        try (PrintWriter toSocket =
                     new PrintWriter(clientSocket.getOutputStream(), true)) {
            toSocket.println("Hello from server " +
                    clientSocket.getInetAddress());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8010;
        //pool size means how many clients the server can handle simultaneously
        int poolSize = 10; // Adjust the pool size as needed 
        Server server = new Server(poolSize);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(70000);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // Use the thread pool to handle the client
                server.threadPool.execute(() ->
                        server.handleClient(clientSocket));
            }
        } catch (SocketTimeoutException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Shutdown the thread pool when the server exits
            server.threadPool.shutdown();
        }
    }
}
