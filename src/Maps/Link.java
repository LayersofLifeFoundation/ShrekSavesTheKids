package Maps;

import java.awt.Graphics;
import java.io.IOException;

import GameStateManager.OverworldState;

public class Link {
	
	/*
	 * Provides a tile to link different maps
	 */
	
	int x,y,toX,toY;
	String linkMovement, linkText;
	
	/*
	 * A bunch of functions to set variables
	 */
	
	public void setX(int x) {
		this.x = x;
	}
	public void setToX(int x) {
		toX = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setToY(int y) {
		toY = y;
	}
	public void setLinkMovement(String s) {
		linkMovement = s;
	}
	public void setLinkText(String s) {
		linkText = s;
	}
	
	
	/*
	 * Changes map if player is on given x and y
	 */
	public void tick() {
		if(OverworldState.player.returnX() == x && OverworldState.player.returnY() == y) {
			try {
				
				OverworldState.player.setX(toX);
				OverworldState.player.setY(toY);
				OverworldState.movMap.loadMap(linkMovement);
				OverworldState.textMap.loadMap(linkText);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void render(Graphics g) {
		
	}
	
}
