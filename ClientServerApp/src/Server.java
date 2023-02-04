import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server Started....");

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected...");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            try {
                // read form client...
                Object clientsms = objectInputStream.readObject();
                System.out.println("From Client "+ (String) clientsms);

                String serversms = (String) clientsms;
                serversms = serversms.toUpperCase();

                // send to client
                objectOutputStream.writeObject(serversms);
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    }
}
