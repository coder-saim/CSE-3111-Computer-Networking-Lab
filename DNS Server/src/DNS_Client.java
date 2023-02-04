
import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class
public class DNS_Client {
    public static void main(String[] args) throws IOException {
        try {
            Scanner scn = new Scanner(System.in);

            InetAddress ip = InetAddress.getByName("localhost");

            Socket s = new Socket(ip, 5000);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());


            while (true) {
                System.out.println(dis.readUTF());
                String tosend = scn.nextLine();
                dos.writeUTF(tosend);


                if(tosend.equals("3")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");

                    // closing resources
                    scn.close();
                    dis.close();
                    dos.close();

                    System.exit(0);
                }

                String receivedtxt = dis.readUTF();
                System.out.println(receivedtxt);

                String send_hostname = scn.nextLine();
                dos.writeUTF(send_hostname);

                String received = dis.readUTF();
                System.out.println(received);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
