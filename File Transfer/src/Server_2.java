import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;





public class Server_2 {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) throws IOException {
        // Here we define Server Socket running on port 5000
        try (ServerSocket serverSocket = new ServerSocket(7500)) {
            System.out.println("Server is Starting in Port 7500");
            // Accept the Client request using accept method

            while (true) {
                Socket s = null;
                try {
                    s = serverSocket.accept();
                    System.out.println("\nA new Client is Connected");
                    dataInputStream = new DataInputStream(s.getInputStream());
                    dataOutputStream = new DataOutputStream(s.getOutputStream());
                    // Here we call receiveFile define new for that
                    System.out.println("Assigning new thread for this client");


                    Thread thread = new ClientHandler(s, dataInputStream, dataOutputStream);
                    thread.start();


//                    dataOutputStream.close();
//                    dataOutputStream.close();
//                    s.close();
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // receive file function is start here





}





class ClientHandler extends Thread {
    final DataInputStream dis;
    static DataOutputStream dos;
    final Socket s;
    private static DataOutputStream dataOutputStream = null;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    public void run() {

        String received;
        String toreturn;
        while (true)
        {
            try {

                // Ask user what he wants
                dos.writeUTF("What do you want?[Pdf | Image | Text]..\n"+ "Type Exit to terminate connection.");

                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("Exit"))
                {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }


                // write on output stream based on the
                // answer from the client
                switch (received) {

                    case "Pdf" :
                        sendFile("/home/coder_saim/IdeaProjects/File Transfer/a.pdf");
                        System.out.println("Pdf received by Client");
                        break;

                    case "Image" :
                        sendFile("/home/coder_saim/IdeaProjects/File Transfer/b.png");
                        System.out.println("Image received by Client");
                        break;

                    case "Text" :
                        sendFile("/home/coder_saim/IdeaProjects/File Transfer/c.txt");
                        System.out.println("Text received by Client");
                        break;

                    default:
                        //dos.writeUTF("Invalid input");
                        break;
                }
            } catch (Exception e) {
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





    private static void sendFile(String path) throws Exception {
//        DataOutputStream dataOutputStream = null;
        int bytes = 0;
        // Open the File where he located in your pc
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        // Here we send the File to Server
        dos.writeLong(file.length());
        // Here we break file into chunks
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer)) != -1) {
            // Send the file to Server Socket
            dos.write(buffer, 0, bytes);
            dos.flush();
        }
        // close the file here
        fileInputStream.close();
//    }
    }
}