import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.net.Socket;
import java.security.PublicKey;

public class MouseHookManager{
    Robot mouse = new Robot();
    Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
    private static MouseHookManager instance = null;

    private Client client = Main.client;
    private MouseHookManager() throws AWTException {
    }

    public static MouseHookManager getInstance() throws AWTException {
        if (instance == null) {
            instance = new MouseHookManager();

        }
        return instance;
    }
    public void HookMsg(String msg, NativeMouseEvent e) throws AWTException {
        //System.out.println(msg);
        if (KeyHookManager.remote){
            int x = (int)res.getWidth()/2;
            int y = (int)res.getHeight()/2;
            if(msg.contains("Pressed") || msg.contains("Released")){
                client.Send(msg + e.getButton());
            }

            client.Send(msg + (e.getPoint().x - x) + "," + (e.getPoint().y - y));
            //System.out.println(msg + (e.getPoint().x - x) + "," + (e.getPoint().y - y));
            Mouse_Center(e);
        }
    }

    public void Mouse_Center(NativeMouseEvent e) throws AWTException {
        //마우스 화면 중앙에 고정
        int x = (int)res.getWidth()/2;
        int y = (int)res.getHeight()/2;
        if (e.getPoint().x > x+1 || e.getPoint().x < x-1 || e.getPoint().y > y+1 || e.getPoint().y < y-1){
            mouse.mouseMove(x,y);
        }
        //mouse.mouseMove(x,y);
    }

    public void Mouse_Event(String msg){
        System.out.println("EVENT : " + msg);
        PointerInfo pt = MouseInfo.getPointerInfo();
        if(msg.contains("Moved")){
            String point = msg.split(":")[2];
            int x = pt.getLocation().x + Integer.parseInt(point.split(",")[0]);
            int y = pt.getLocation().y + Integer.parseInt(point.split(",")[1]);
            mouse.mouseMove(x,y);
        }
        if (msg.contains("Pressed")){
            String button = msg.split(":")[2];
            if(button.contains(",")){
                return;
            }
            if (button.equals("1")){
                mouse.mousePress(InputEvent.BUTTON1_MASK);
            }else if (button.equals("3")) {
                mouse.mousePress(InputEvent.BUTTON2_MASK);
            }else if (button.equals("2")) {
                mouse.mousePress(InputEvent.BUTTON3_MASK);
            }

        }
        if (msg.contains("Released")){
            String button = msg.split(":")[2];
            if(button.contains(",")){
                return;
            }
            if (button.equals("1")) {
                mouse.mouseRelease(InputEvent.BUTTON1_MASK);
            }else if (button.equals("3")) {
                mouse.mouseRelease(InputEvent.BUTTON2_MASK);
            }else if (button.equals("2")) {
                mouse.mouseRelease(InputEvent.BUTTON3_MASK);
            }
        }
    }
}
