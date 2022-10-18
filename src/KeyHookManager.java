import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.awt.*;

public class KeyHookManager {

    public static boolean remote = false;
    private static KeyHookManager instance = null;
    private KeyHookManager() {
    }
    public static KeyHookManager getInstance() {
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
            //Main.client.Send(msg);
            System.out.println(msg);
        }
    }

}
