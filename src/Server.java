import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    MouseHookManager mouseHookManager = MouseHookManager.getInstance();

    public Server() throws AWTException {
    }

    public void run(){
        try(ServerSocket serverSocket = new ServerSocket(Main.PORT)) {

            System.out.println("Ready... ");

            Socket socket = serverSocket.accept();
            System.out.println("Connect : " + socket.getInetAddress());
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            while (true){
                String msg = dataInputStream.readUTF();
                System.out.println(msg);
                mouseHookManager.Mouse_Event(msg);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
