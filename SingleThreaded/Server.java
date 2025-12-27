import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
public class Server{
    public void run() throws IOException{
        int port = 8010;
        //port number to listen on
        //create server socket
        ServerSocket socket = new ServerSocket(port);
        //set timeout to 10 seconds which menas if no connection is received in 10 seconds the it will close the socket
        socket.setSoTimeout(10000);
        while(true){
            try{
                System.out.println("Server is listening on port"+port);
                //accept connection from client
                Socket acceptedConnection=socket.accept();
                //print the remote socket address of the client , acceptedConnection.getRemoteSocketAddress() returns the address of the client
                System.out.println("Connection accepted from"+acceptedConnection.getRemoteSocketAddress());
                //printwriter to send data to client in text format
                // getOutputStream() returns the output stream of the socket
                PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream());
                //bufferedreader to read data from client in text format 
                //getInputStream() returns the input stream of the socket
                BufferedReader fromClient=new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                //send message to client
                toClient.println("Hello from the server");
                toClient.close();
                fromClient.close();
                acceptedConnection.close();

            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    public static void main(String args[]){
        Server server=new Server();
        try{
            server.run();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}