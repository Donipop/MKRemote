import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;

public class CustomKeyHook implements NativeKeyListener {
    KeyHookManager keyHookManager = KeyHookManager.getInstance();
    public void nativeKeyPressed(NativeKeyEvent e) {
//        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        try {
            keyHookManager.HookMsg("Key:Pressed:",e);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
//        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        try {
            keyHookManager.HookMsg("Key:Released:",e);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
        try {
            keyHookManager.HookMsg("Key:Typed:",e);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }
    }

}
