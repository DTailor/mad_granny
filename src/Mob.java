import java.awt.*;

public class Mob extends Rectangle{
	public int mobSize = 26;
	public int mobId=Value.mobAir;
	public boolean inGame = false;
	
	public Mob(){
		
	}
	
	public void spawnMob(int mobID){
		for(int i =0; y<Screen.room.block.length;y++){
			if(Screen.room.block[y][0].groundID == Value.groundRoad){
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, mobSize, mobSize);
			}
		}
		this.mobId = mobID;
		inGame=true;
	}
	
	public void physic() {
		
	}
	
	public void draw(Graphics g){
		g.drawImage(Screen.tileset_granny[mobId], x, y, width, height, null);
	}
}
