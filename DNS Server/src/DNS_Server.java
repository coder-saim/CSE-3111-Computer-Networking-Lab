
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

// Server class
public class DNS_Server {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server is Running...");


        while (true) {
            Socket s = null;

            try {
                s = ss.accept();

                System.out.println("\nA new client is connected : " + s);

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}

// ClientHandler class
class ClientHandler extends Thread {

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String received;
        String toreturn;
        while (true) {
            try {

                // Ask user what he wants
                dos.writeUTF("\nMenu: \n1. DNS 2. Reverse DNS 3. Exit\nEnter your choice");

                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("3"))
                {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed\n");
                    break;
                }




                switch (received) {

                    case "1" :
                        dos.writeUTF("\nEnter Host Name which you want...");
                        String hostname = dis.readUTF();
                        System.out.println("Provided HostName is: " + hostname);
                        InetAddress address;
                        address = InetAddress.getByName(hostname);
                        dos.writeUTF("Host Name: " + address.getHostName() + "\nIP: " + address.getHostAddress());
                        break;

                    case "2" :
                        dos.writeUTF("\nEnter Ip Address which you want...");
                        String ipads = dis.readUTF();
                        System.out.println("Provided HostName is: " + ipads);
                        InetAddress ipaddress;
                        ipaddress = InetAddress.getByName(ipads);
                        dos.writeUTF("Host Name: " + ipaddress.getHostName() + "\nIP: " + ipaddress.getHostAddress());
                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
