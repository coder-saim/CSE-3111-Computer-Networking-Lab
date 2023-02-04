import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        System.out.println("Client Started....");
        Socket socket = new Socket("127.0.0.1",22222);
        System.out.println("Client Connected....");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        Scanner scanner = new Scanner(System.in);

        String sms = scanner.nextLine();
        objectOutputStream.writeObject(sms);

        try {
            Object fromServer = objectInputStream.readObject();
            System.out.println("From Server " + (String) fromServer);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
