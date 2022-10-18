import com.github.kwhat.jnativehook.mouse.*;

import java.awt.*;

public class CustomMouseHook implements NativeMouseListener{
    MouseHookManager mouseHookManager = MouseHookManager.getInstance();

    public CustomMouseHook() throws AWTException {
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        try {
            mouseHookManager.HookMsg("Mouse:Click:",e);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        try {
            mouseHookManager.HookMsg("Mouse:Pressed:",e);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        try {
            mouseHookManager.HookMsg("Mouse:Released:",e);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }
    }

}
class CustomMouseMotionHook implements NativeMouseMotionListener{
    MouseHookManager mouseHookManager = MouseHookManager.getInstance();

    CustomMouseMotionHook() throws AWTException {
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        try {
            mouseHookManager.HookMsg("Mouse:Dragged:",e);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        try {
            mouseHookManager.HookMsg("Mouse:Moved:",e);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }
    }
}
class CustomMouseWheelHook implements NativeMouseWheelListener {
    MouseHookManager mouseHookManager = MouseHookManager.getInstance();

    CustomMouseWheelHook() throws AWTException {
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        //System.out.println("Mouse Wheel Moved: " + e.getWheelRotation());
        //왼쪽에서 오른쪽 방향 = 4 회전 = 마이너스 , 오른쪽에서 왼쪽 방향 = 4 회전 = 플러스  5이상이면 앞으로가기 뒤로가기키 적용 회전력이 ㅇㅇ
        //올라가는건 마이너스 내려가는건 플러스
        try {
            mouseHookManager.HookMsg("Mouse:Wheel:",e);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }
    }
}