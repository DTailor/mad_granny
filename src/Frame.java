import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
	public static String title = "Mad Grannies";
	public static Dimension size = new Dimension(600,500);
	
	
	public Frame(){
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
	}
	
	
	public void init(){
		setVisible(true);
	}
	
	public static void main(String args[]){
		Frame frame = new Frame();
	}
	
}
