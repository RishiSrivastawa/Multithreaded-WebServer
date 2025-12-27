import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    // Method to create a Runnable for client operations
    // Each Runnable will connect to the server, send a message, and print the response
    // This allows multiple clients to run concurrently in separate threads
    public Runnable getRunnable() {
        // Lambda expression that implements the Runnable interface
        return () -> {
            // Server port number
            int port = 8010;
            // Establish connection to the server
            try {
                // Get the localhost address for connecting to the server
                //inetaddress is used to get the IP address of the localhost
                InetAddress address = InetAddress.getByName("localhost");
                try (
                    // Create a socket to connect to the server
                    Socket socket = new Socket(address, port);
                    // Create input and output streams for communication with the server
                    PrintWriter toServer =
                        new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader fromServer =
                        new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))
                ) {
                    // Send a message to the server
                    toServer.println(
                        "Hello from client " + socket.getLocalSocketAddress()
                    );
                    // Read and print the response from the server
                    String response = fromServer.readLine();
                    System.out.println(
                        Thread.currentThread().getName() +
                        " received: " + response
                    );
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();
        // Start 100 client threads
        for (int i = 0; i < 100; i++) {
            // Create and start a new client thread
            Thread thread =
                new Thread(client.getRunnable(), "Client-" + i);
            thread.start();
        }
    }
}
