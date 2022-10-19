import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class KeyHookManager {

    Robot robot = new Robot();
    public static boolean remote = false;
    private static KeyHookManager instance = null;
    private KeyHookManager() throws AWTException {
    }
    public static KeyHookManager getInstance() throws AWTException {
        if (instance == null) {
            instance = new KeyHookManager();
        }
        return instance;
    }

    public void HookMsg(String msg, NativeKeyEvent e) throws AWTException {
        //System.out.println(msg);

        if(msg.equals("Key:Pressed:") && e.getKeyCode() == NativeKeyEvent.VC_F1){
            remote = !remote;
            System.out.println("remote : " + remote);
        }

        if(remote){
            //마우스 화면 중앙에 고정
            Main.client.Send(msg + e.getKeyCode());
            System.out.println("ㅇㅕ기" + msg + e.getKeyCode() + "," + NativeKeyEvent.getKeyText(e.getKeyCode()));
            System.out.println("진짜 키 : " + KeyEvent.getExtendedKeyCodeForChar(e.getKeyCode()));
        }
    }
    public void Key_Event(String msg){

        System.out.println("EVENT : " + msg);
        if(msg.contains("Pressed")){
            String key = msg.split(":")[2];
            if(key.contains(",")){
                key = key.split(",")[0]  ;
            }
            robot.keyPress(Integer.parseInt(key,16));
        }
        if(msg.contains("Released")){
            String key = msg.split(":")[2];
            if(key.contains(",")){
                key = key.split(",")[0];
            }
            robot.keyRelease(Integer.parseInt(key,16));

        }
    }

}
