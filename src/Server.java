import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    public void run(){
        try(ServerSocket serverSocket = new ServerSocket(Main.PORT)) {

            System.out.println("Ready... ");

            Socket socket = serverSocket.accept();
            System.out.println("Connect : " + socket.getInetAddress());
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            while (true){
                System.out.println(dataInputStream.readUTF());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
