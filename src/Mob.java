import java.awt.*;

public class Mob extends Rectangle{
	public int xC=0,yC=0;
	public int mobSize = 52;
	public int mobId=Value.mobAir;
	public boolean inGame = false;
	public int mobWalk = 0;
	public int up = 0, down=1, right=2;
	public int direction = right;
	public boolean isUp = false;
	public boolean isDown = false;

	
	public Mob(){
		
	}
	
	public void spawnMob(int mobID){
		for(int y =0; y<Screen.room.block.length;y++){
			if(Screen.room.block[y][0].groundID == Value.groundRoad){
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, mobSize, mobSize);
				xC = 0;
				yC= y; 
			}
		}
		this.mobId = mobID;
		inGame=true;
	}
	
	
	public int walkFrame=0, walkSpeed=20;
	public void physic() {
		if(walkFrame>=walkSpeed){
			if(direction==right){
				x+=1;
			}else if(direction==up){
				y-= 1;
			}else if(direction==down){
				y+=1;
			}
			
			mobWalk += 1;
			if(mobWalk == Screen.room.blockSize){
				
				if(direction==right){
					xC+=1;
				}else if(direction==up){
					yC-= 1;
					isUp = true;
				}else if(direction==down){
					yC+=1;
					isDown = true;
				}
				
				if(!isUp){
					try{
						if(Screen.room.block[yC+1][xC-1].groundID==Value.groundRoad){
							direction = down;
						}
					} catch(Exception e){}
				}
				try{
					if(Screen.room.block[yC][xC+1].groundID==Value.groundRoad){
						direction = right;
					}
				} catch(Exception e){}
				if(!isDown){
					try{
						if(Screen.room.block[yC-1][xC-1].groundID==Value.groundRoad && Screen.room.block[yC][xC+1].groundID == Value.groundGrass ){
							direction = up;
						}
					} catch(Exception e){}
				}
				
				
				isUp = false;
				isDown = false;
				mobWalk = 0;
			}
			
			walkFrame =0;
		}
		else{
			walkFrame+=1;
		}
		
	}
	
	public void draw(Graphics g){
		g.drawImage(Screen.tileset_granny[mobId], x, y, width, height, null);
	}
}
