import java.awt.*;

public class Block extends Rectangle 
{
	public Rectangle towerSquare;
	public int towerSquareSize = 100;
    public int groundID;
    public int airID;
    public int loseTime=50, loseFrame=0;
    public int shotMob = -1;
    public boolean shooting = false;
     
    public Block(int x, int y, int width, int height, int groundID, int airID)
    {
        setBounds(x, y, width, height);
        towerSquare = new Rectangle(x-(towerSquareSize/2),y-(towerSquareSize/2), width+(towerSquareSize), height+(towerSquareSize));
        this.groundID = groundID;
        this.airID = airID;
    }
     
    public void draw(Graphics g)
    {
        g.drawImage(Screen.tileset_ground[groundID], x, y, width, height, null);
         
        if( airID != Value.airAir)
        {
            g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);
            
        }
    }
    
    
    public void physic(){
    	if(shotMob!= -1 && towerSquare.intersects(Screen.mobs[shotMob])){
			shooting = true;
		}else{
			shooting= false;
		}
    	
    	if(!shooting){
	    	if(airID == Value.turkeyTower){
		    	for(int i=0;i<Screen.mobs.length;i++){
		    		if(Screen.mobs[i].inGame){
		    			if(towerSquare.intersects(Screen.mobs[i])){
		    				shooting = true;
		    				shotMob = i;
		    			}
		    		}
		    	}
	    	}
    	}
    	
    	if(shooting){
    		if(loseFrame>=loseTime){
    		Screen.mobs[shotMob].loseHealth(1);
    		loseFrame = 0;
    		}else{loseFrame += 1;}
    		if(Screen.mobs[shotMob].isDead()){
    			shooting = false;
    			shotMob = -1;
    		}    		
    	}
    }
    
    public void fight(Graphics g){
    	if(Screen.isDebug){
    		if(airID == Value.turkeyTower){
            	g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
            }
    	}
    	if(shooting){
    		g.setColor(new Color(255,255,255));
    		g.drawLine(x+(width/2), y+(height/2), Screen.mobs[shotMob].x + (width/2), Screen.mobs[shotMob].y + (height/2));
    	
    	}
    }
}
