import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.function.Consumer;
public class Server{
    //return new Consumer<Socket>(){
        // @Override
        // public void accept(Socket clientSocket){
        //     try{
        //         PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
        //         toClient.println("Hello from the server");
        //         toClient.close();
        //         clientSocket.close();
        //     }catch(IOException ex){
        //         ex.printStackTrace();
        //     }
        // }
    //};

    //diffrence between above and below is that below is using lambda expression and above is using anonymous class
    // the lambda expression is more concise and easier to read than the anonymous class

    //here we are defining a method that returns a Consumer<Socket> using a lambda expression
    //customer<Socket> is a functional interface that has a single abstract method accept(Socket t)
    public Consumer<Socket> getConsumer(){
        //returns a lambda expression that implements the accept method of the Consumer<Socket> interface
        return (clientSocket)->{
            try{
                //inside the lambda expression we are writing the code that will be executed when the accept method is called
                //printWriter is used to send data to the client because it provides convenient methods for writing text data to the output stream
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                //sending a message to the client
                toClient.println("Hello from the server");
                toClient.close();
                clientSocket.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        };
    }
    public static void main(String[] args) {
        int port=8010;
        Server server = new Server();
        try{
            //creating a server socket that listens on the specified port
            ServerSocket socketSocket = new ServerSocket(port);
            socketSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port " + port);
            while(true){
                //accepting a client connection and creating a new thread to handle the connection
                Socket acceptedSocket = socketSocket.accept();
                Thread thread = new Thread(()-> server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

//this code is different from the single threaded server because it can handle multiple client connections simultaneously
//the flow of execution is as follows:
//1. the server listens for incoming client connections on a specified port (8010 in this case)
//2. when a client connects, the server accepts the connection and creates a new thread to handle the connection
//3. the new thread executes the code defined in the lambda expression returned by the getConsumer() method
//4. the lambda expression sends a message to the client and closes the connection
