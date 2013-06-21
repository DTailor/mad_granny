import java.awt.*;

import sun.security.jgss.krb5.Krb5Util.ServiceCreds;


public class Store {
	public static int shopWidth = 8;
	public static int buttonSize = 52;
	public static int cellSpace = 2;
	public static int iconSize = 20;
	public static int[] buttonID = {0,Value.turkeyTower,Value.rabbitTower,Value.kabanTower,Value.hameakTower,5,5,6,7};
	public static int[] buttonPrice = {0,5, 15, 25, 50, 0, 0, 0, 0, };
	public static int heldID= -1;
	public Rectangle[] button = new Rectangle[shopWidth];
	
	public Rectangle buttonHealth;
	public Rectangle buttonCoins;
	
	public static int iconSpace = 3;
	public static int iconTextY = 15;
	public boolean holdsItem = false;
	
	
	public Store(){
		define();
	}
	
	
	public void click(int mouseButton){
		if(mouseButton == 1){
			for(int i=0; i<button.length;i++){
				if(button[i].contains(Screen.mouse)){
					if(heldID== Value.delete){
						heldID = -1;
						holdsItem = false;
					}
					heldID = buttonID[i];
					holdsItem = true;
				}
			}
			if(holdsItem){
				if(Screen.coins >= buttonPrice[heldID]){
					for(int y=0; y<Screen.room.block.length;y++){
						for(int x=0; x<Screen.room.block[0].length;x++){
							if(Screen.room.block[y][x].contains(Screen.mouse)){
								if(Screen.room.block[y][x].groundID != Value.groundRoad && Screen.room.block[y][x].airID == Value.airAir){
									Screen.room.block[y][x].airID = heldID;
									Screen.coins -= buttonPrice[heldID];
								}
							}
						}
					}
				}
				
			}
		}
	}
	
	public void define(){
		for(int i=0;i<button.length;i++){
			button[i] = new Rectangle((Screen.myWidth/2)-(shopWidth*(buttonSize+cellSpace)/2) + ((buttonSize+cellSpace)*i), (Screen.room.block[Screen.room.worldHeight-1][0].y)+Screen.room.blockSize+cellSpace*10, buttonSize, buttonSize);
		}
		
		buttonHealth = new Rectangle(Screen.room.block[0][0].x-1, button[0].y, iconSize, iconSize);
		buttonCoins = new Rectangle(Screen.room.block[0][0].x -1, button[0].y + button[0].height - iconSize,iconSize,iconSize);
	}
	
	public void draw(Graphics g){
		
		
		for(int i=0;i<button.length;i++){
			if(button[i].contains(Screen.mouse)){
				g.setColor(new Color(255,255,255, 100));
				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}
			g.drawImage(Screen.tileset_res[0], button[i].x, button[i].y, button[i].width, button[i].height, null);
			g.drawImage(Screen.tileset_shop[buttonID[i]], button[i].x, button[i].y, button[i].width, button[i].height, null);
			if(buttonPrice[i]>0){
				g.setColor(new Color(255,255,255));
				g.setFont(new Font("Courier New", Font.BOLD, 14));
				g.drawString(buttonPrice[i]+"",button[i].x+20, button[i].y+65);
			}
		}
		
		g.drawImage(Screen.tileset_res[1],buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null);
		g.drawImage(Screen.tileset_res[2],buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height,null);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.setColor(new Color(255,255,255));
		g.drawString(""+Screen.health, buttonHealth.x+buttonHealth.width + iconSize, buttonHealth.y+iconTextY);
		g.drawString(""+Screen.coins, buttonCoins.x+buttonCoins.width + iconSize, buttonCoins.y+iconTextY);
		
		if(holdsItem){
			g.drawImage(Screen.tileset_shop[heldID], Screen.mouse.x, Screen.mouse.y, button[0].width, button[0].height, null);
		}
	}
}
