import java.awt.*;

public class Block extends Rectangle 
{
	public Rectangle towerSquare;
	public int towerSquareSize = 100;
	public Rectangle towerSquareRabbit;
	public int towerSquareSizeRabbit = 200;
	public Rectangle towerSquareKaban;
	public int towerSquareSizeKaban = 100;
	public Rectangle towerSquareHameak;
	public int towerSquareSizeHameak = 500;
    public int groundID;
    public int airID;
    public int loseTime=50, loseFrame=0;
    public int shotMob = -1;
    public boolean shooting = false;
     
    public Block(int x, int y, int width, int height, int groundID, int airID)
    {
        setBounds(x, y, width, height);
        if(airID == Value.turkeyTower){
        	
        }
        if(airID == Value.rabbitTower){
        	towerSquareSize = towerSquareSizeRabbit;
        }
        if(airID == Value.kabanTower){
        	towerSquareSize = towerSquareSizeKaban;
        }
        if(airID == Value.hameakTower){
        	towerSquareSize = towerSquareSizeHameak;
        }
        towerSquare = new Rectangle(x-(towerSquareSize/2),y-(towerSquareSize/2), width+(towerSquareSize), height+(towerSquareSize));
        towerSquareRabbit = new Rectangle(x-(towerSquareSizeRabbit/2),y-(towerSquareSizeRabbit/2), width+(towerSquareSizeRabbit), height+(towerSquareSizeRabbit));
        towerSquareKaban = new Rectangle(x-(towerSquareSizeKaban/2),y-(towerSquareSizeKaban/2), width+(towerSquareSizeKaban), height+(towerSquareSizeKaban));
        towerSquareHameak = new Rectangle(x-(towerSquareSizeHameak/2),y-(towerSquareSizeHameak/2), width+(towerSquareSizeHameak), height+(towerSquareSizeHameak));        
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
    	
    	
    	if(airID == Value.turkeyTower){
    		if(shotMob!= -1 && towerSquare.intersects(Screen.mobs[shotMob])){
    			shooting = true;
    		}else{
    			shooting= false;
    		}
        }
		if(airID == Value.rabbitTower){
			if(shotMob!= -1 && towerSquareRabbit.intersects(Screen.mobs[shotMob])){
				shooting = true;
			}else{
				shooting= false;
			}
        }
		if(airID == Value.kabanTower){
			if(shotMob!= -1 && towerSquareKaban.intersects(Screen.mobs[shotMob])){
				shooting = true;
			}else{
				shooting= false;
			}
        }
		if(airID == Value.hameakTower){
			if(shotMob!= -1 && towerSquareHameak.intersects(Screen.mobs[shotMob])){
				shooting = true;
			}else{
				shooting= false;
			}
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
	    	if(airID == Value.rabbitTower){
		    	for(int i=0;i<Screen.mobs.length;i++){
		    		if(Screen.mobs[i].inGame){
		    			if(towerSquareRabbit.intersects(Screen.mobs[i])){
		    				shooting = true;
		    				shotMob = i;
		    			}
		    		}
		    	}
	    	}
	    	if(airID == Value.kabanTower){
		    	for(int i=0;i<Screen.mobs.length;i++){
		    		if(Screen.mobs[i].inGame){
		    			if(towerSquareKaban.intersects(Screen.mobs[i])){
		    				shooting = true;
		    				shotMob = i;
		    			}
		    		}
		    	}
	    	}
	    	if(airID == Value.hameakTower){
		    	for(int i=0;i<Screen.mobs.length;i++){
		    		if(Screen.mobs[i].inGame){
		    			if(towerSquareHameak.intersects(Screen.mobs[i])){
		    				shooting = true;
		    				shotMob = i;
		    			}
		    		}
		    	}
	    	}
    	}
    	
    	if(shooting){
    		if(loseFrame>=loseTime){
    		
    		if(airID == Value.turkeyTower){
    			Screen.mobs[shotMob].loseHealth(1);
        		loseFrame = 0;
            }
    		if(airID == Value.rabbitTower){
    			Screen.mobs[shotMob].loseHealth(2);
        		loseFrame = 0;
            }
    		if(airID == Value.kabanTower){
    			Screen.mobs[shotMob].loseHealth(5);
        		loseFrame = 0;
            }
    		if(airID == Value.hameakTower){
    			Screen.mobs[shotMob].loseHealth(3);
        		loseFrame = 0;
            }
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
    		if(airID == Value.rabbitTower){
            	g.drawRect(towerSquareRabbit.x, towerSquareRabbit.y, towerSquareRabbit.width, towerSquareRabbit.height);
            }
    		if(airID == Value.kabanTower){
            	g.drawRect(towerSquareKaban.x, towerSquareKaban.y, towerSquareKaban.width, towerSquareKaban.height);
            }
    		if(airID == Value.hameakTower){
            	g.drawRect(towerSquareHameak.x, towerSquareHameak.y, towerSquareHameak.width, towerSquareHameak.height);
            }
    	}
    	if(shooting){
    		g.setColor(new Color(255,255,255));
    		if(airID == Value.rabbitTower){
    			g.setColor(new Color(255,0,0));
    		}
    		if(airID == Value.kabanTower){
    			g.setColor(new Color(185,48,101));
    		}
    		if(airID == Value.hameakTower){
    			g.setColor(new Color(255,0,100));
    		}
    		g.drawLine(x+(width/2), y+(height/2), Screen.mobs[shotMob].x + (width/2), Screen.mobs[shotMob].y + (height/2));
    	
    	}
    }
}
