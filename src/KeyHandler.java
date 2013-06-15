import java.awt.event.*;
import java.awt.*;
public class KeyHandler implements MouseListener, MouseMotionListener{

	
	public void mouseDragged(MouseEvent e) {
		Screen.mouse = new Point(e.getX() - ((Frame.size.width-Screen.myWidth)/2),(e.getY())+((Frame.size.height-(Screen.myHeight))-(Frame.size.width- Screen.myWidth)/2));
		
	}

	
	public void mouseMoved(MouseEvent e) {
		Screen.mouse = new Point(e.getX() - ((Frame.size.width-Screen.myWidth)/2),(e.getY())+((Frame.size.height-(Screen.myHeight))-(Frame.size.width- Screen.myWidth)/2));
		
	}

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
