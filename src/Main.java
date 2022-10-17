import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import sun.net.www.http.HttpClient;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NativeHookException {
        Main main = new Main();

//        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new CustomKeyHook());
        GlobalScreen.addNativeMouseListener(new CustomMouseHook());
        GlobalScreen.addNativeMouseMotionListener(new CustomMouseMotionHook());
        GlobalScreen.addNativeMouseWheelListener(new CustomMouseWheelHook());

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Server 2. Client");
        int mode = sc.nextInt();
        if (mode == 1) {
            main.Server();
        } else {
            main.Client();
        }
    }
    private void Server(){
        System.out.println("Server");
        System.out.println("Your IP : " + getIP());
    }
    private void Client(){
        System.out.println("Client");
    }

    private String getIP() {
        String hostAddr = get("https://ifconfig.me/ip");
        return hostAddr;
    }

    public static String get(String getUrl) {
        URL url = null;
        try {
            url = new URL(getUrl);
        } catch (MalformedURLException e1) {
            throw new RuntimeException("URL형식을 확인해주세요.", e1);
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e1) {
            throw new RuntimeException("연결 중 입출력 오류가 발생하였습니다.", e1);
        }
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException("연결 중 프로토콜 오류가 발생하였습니다.", e);
        }

        con.setRequestProperty("Content-Type", "text/html");
        con.setConnectTimeout(5000); // 연결 타임아웃
        con.setReadTimeout(5000); // 읽기 타임아웃
        con.setInstanceFollowRedirects(true); // 리다이렉트 자동

        try {
            int status = con.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("정상 응답 코드가 아닙니다. " + status);
            }
        } catch (IOException e) {
            throw new RuntimeException("응답을 받는 중 오류가 발생하였습니다.", e);
        }
        StringBuffer content = new StringBuffer();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("응답을 읽는 중 오류가 발생하였습니다.", e);
        }
        con.disconnect();
        return content.toString();
    }
}