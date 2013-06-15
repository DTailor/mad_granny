import java.awt.*;

import javax.swing.*;

public class Screen extends JPanel implements Runnable{
	public Thread thread = new Thread(this);
	public static boolean isFirst = true;
	public static int myWith, myHeight;
	
	public static Room room;
	
	public Screen(){
		thread.start();
	}
	
	public void define(){
		room = new Room();
	}
	
	public void paintComponent(Graphics g){
		if(isFirst){
			
			define();
			isFirst = false;
		}
		
		g.clearRect(0, 0, getWidth(), getHeight());
		
		room.draw(g);  // Draw the map
	}
	
	public static int fpsFrame = 0, fps = 1000000;
	
	public void run(){
		while(true){
			if(!isFirst){
				room.physic();
			}
			
			
			repaint();
			
			try{
				Thread.sleep(1);
			} catch(Exception e){}
		
		}
	}
}