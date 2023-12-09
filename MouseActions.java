import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
public class MouseActions implements MouseListener, MouseMotionListener {
    private int mouseX, mouseY;

    public MouseActions(){
    }

    public void mouseMoved(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseDragged(MouseEvent e){
        System.out.println("Dragged Mouse");
    }

    public Rectangle getMouseBounds(){
        return new Rectangle(mouseX,mouseY,1,1);
    }
    
    public void mouseExited(MouseEvent e){

    }

    public void mouseReleased(MouseEvent e){

    }

    public void mouseClicked(MouseEvent e){

    }

    public void mousePressed(MouseEvent e){

    }

    public void mouseEntered(MouseEvent e){

    }

    public String updateMousePos(){
       return(mouseX + " " + mouseY);
    }

}
