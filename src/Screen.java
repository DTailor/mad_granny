import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Image;
import javax.swing.*;

import java.io.*;

public class Screen extends JPanel implements Runnable{
	public Thread thread = new Thread(this);
	public static boolean isFirst = true;
	public static int myWidth, myHeight;
	public static int coins = 20, health = 10;
	
	public static Image[]  tileset_ground = new Image[20];
	public static Image[]  tileset_air = new Image[20];
	public static Image[] tileset_res = new Image[20];
	public static Image[] tileset_granny = new Image[100];
	
	public static Room room;
	public static Save save;
	public static Store store;
	
	public static Mob[] mobs = new Mob[100];
	
	public static Point mouse = new Point(0,0);
	
	public Screen(Frame frame){
		frame.addMouseListener(new KeyHandler());
		frame.addMouseMotionListener(new KeyHandler());
		
		thread.start();
	}
	
	public void define(){
		room = new Room();
		save = new Save();
		store = new Store();
		
		
		
		for(int i = 0;i<tileset_ground.length;i++ ){
			tileset_ground[i] = new ImageIcon("res/tileset_ground.png").getImage();
			tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 52*i, 52, 52)));
		}
		
		for(int i = 0;i<tileset_ground.length;i++ ){
			tileset_air[i] = new ImageIcon("res/tileset_air.png").getImage();
			tileset_air[i] = createImage(new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0, 52*i, 52, 52)));
		}
		
		tileset_res[0] = new ImageIcon("res/cell.png").getImage();
		tileset_res[1] = new ImageIcon("res/weight_icon.png").getImage();
		tileset_res[2] = new ImageIcon("res/tooth.png").getImage();
		
		tileset_granny[0] = new ImageIcon("res/zombo_granny_26px.png").getImage();
		save.loadWorld(new File("save/mission1.grn"));

		for(int i=0; i<mobs.length;i++){
			mobs[i] = new Mob();
			mobs[i].spawnMob(0);
		}
		
	}
	
	public void paintComponent(Graphics g){
		if(isFirst){
			myWidth = getWidth();
			myHeight = getHeight();
			define();
			isFirst = false;
		}
		
		g.setColor(new Color(46,47,48));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(0,0,0));
		g.drawLine(room.block[0][0].x-1, 0, room.block[0][0].x-1, room.block[room.worldHeight-1][0].y+room.blockSize+1);
		g.drawLine(room.block[0][room.worldWidth-1].x+ room.blockSize, 0, room.block[0][room.worldWidth-1].x+ room.blockSize, room.block[room.worldHeight-1][0].y+room.blockSize);
		g.drawLine(room.block[0][0].x, room.block[room.worldHeight-1][0].y+ room.blockSize, room.block[0][room.worldWidth-1].x + room.blockSize,  room.block[room.worldHeight-1][0].y+ room.blockSize);
		
		room.draw(g);  // Draw the map
		
		for(int i=0; i<mobs.length;i++){
			if(mobs[i].inGame){
				mobs[i].draw(g);
			}
		}
		
		store.draw(g);
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
