import com.github.kwhat.jnativehook.mouse.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public class CustomMouseHook implements NativeMouseListener{
    public void nativeMouseClicked(NativeMouseEvent e) {
        System.out.println("Mouse Clicked: " + e.getPoint());
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        System.out.println("Mouse Pressed: " + e.getPoint());
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        System.out.println("Mouse Released: " + e.getPoint());
    }

}
class CustomMouseMotionHook implements NativeMouseMotionListener{
    public void nativeMouseDragged(NativeMouseEvent e) {
        System.out.println("Mouse Dragged: " + e.getPoint());
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        System.out.println("Mouse Moved: " + e.getPoint());
    }
}
class CustomMouseWheelHook implements NativeMouseWheelListener {
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        //System.out.println("Mouse Wheel Moved: " + e.getWheelRotation());
        System.out.println("Mouse Wheel Moved: " + e.getWheelDirection() + " " + e.getWheelRotation());
        //왼쪽에서 오른쪽 방향 = 4 회전 = 마이너스 , 오른쪽에서 왼쪽 방향 = 4 회전 = 플러스  5이상이면 앞으로가기 뒤로가기키 적용 회전력이 ㅇㅇ
        //올라가는건 마이너스 내려가는건 플러스
    }
}