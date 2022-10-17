import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Client{
    private static Socket socket = null;

    private String hostname = "";

    private Client() {
    }
    public Client(String hostname){
        this.hostname = hostname;
    }
    public static Socket getInstance() {
        if(socket==null){
            socket = new Socket();
        }
        return socket;
    }
    public void Connect(Socket socket){
        try{
            SocketAddress address = new InetSocketAddress(hostname,Main.PORT);
            socket.connect(address);
            System.out.println("Connect!");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void Send(String msg){
        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
            System.out.println("보냄 : " + msg);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
