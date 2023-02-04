import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Client_2 {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args)
    {
        // Create Client Socket connect to port 900
        try (Socket socket = new Socket("127.0.0.1", 7500)) {

            Scanner scn = new Scanner(System.in);

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println("Requesting the File from the Server");
                System.out.println(dataInputStream.readUTF());
                String tosend = scn.nextLine();
                dataOutputStream.writeUTF(tosend);


                switch (tosend) {

                    case "Pdf" :
                        receiveFile("Pdf_File.pdf");
                        break;

                    case "Image" :
                        receiveFile("Image_File.png");
                        break;

                    case "Text" :
                        receiveFile("Text_File.txt");
                        break;

                    case "Exit" :
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid input");
                        break;
                }

            }
//            dataInputStream.close();
//            dataInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void receiveFile(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong(); // read file size
        byte[] buffer = new byte[4 * 1024];
        while (size > 0
                && (bytes = dataInputStream.read(
                buffer, 0,
                (int)Math.min(buffer.length, size)))
                != -1) {
            // Here we write the file using write method
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes; // read upto file size
        }
        // Here we received file
        System.out.println("File is Received");
        fileOutputStream.close();
    }

}