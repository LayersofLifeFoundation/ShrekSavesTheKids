package Player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Battle.BattleState;
import GameStateManager.Game;
import GameStateManager.OverworldState;

public class Player {

	public static int x = 7;
	public static int y = 10;
	public static BufferedImage spriteSheet;
	public static BufferedImage currentSprite;
	int battlePer = 20;
	int noAnime = 0;
	public static boolean isMoving = false;
	boolean moveUp = false;
	int upAnime = 0;
	int downAnime = 0;
	static int OGPIX = 48;
	boolean moveDown = false;
	int rightAnime = 0;
	boolean moveRight = false;
	int leftAnime = 0;
	boolean moveLeft = false;
	int punLeftAnime = 0;
	public boolean punLeft = false;
	int punRightAnime = 0;
	public boolean punRight = false;
	int dieAnime = 0;
	public static boolean dab = false;
	boolean dabState = false;
	public String facing = "Down";

	public static BufferedImage grabImage(BufferedImage img, int row, int col) {
		BufferedImage image = img.getSubimage((col - 1) * OGPIX, (row - 1) * OGPIX, OGPIX, OGPIX);
		return image;
	}

	public Player() {
		isMoving = false;

		try {
			spriteSheet = ImageIO.read(getClass().getResource("/Sprite_Sheet.png"));
		} catch (IOException e) {
		}
		currentSprite = grabImage(spriteSheet, 3, 1);
	}

	/*
	 * Functions for the four directions that the player can move
	 */
	public void moveUp() throws InterruptedException {
		if (!OverworldState.inDialog) {
			if (!moveUp && !isMoving) {
				if (OverworldState.movMap.canMoveTo(x, y - 1) != 1 && !OverworldState.NPCPresent(x, y - 1)) {
					y -= 1;
					
				}
				moveUp = true;
				facing = "Up";
			}
		}
	}

	public void moveDown() throws InterruptedException {
		if (!OverworldState.inDialog) {
			if (!moveDown && !isMoving) {
				if (OverworldState.movMap.canMoveTo(x, y + 1) != 1 && !OverworldState.NPCPresent(x, y + 1)) {
					y += 1;
					
				}
				moveDown = true;
				facing = "Down";
			}
		}
	}

	public void moveLeft() throws InterruptedException {
		if (!OverworldState.inDialog) {
			if (!moveLeft && !isMoving) {
				if (OverworldState.movMap.canMoveTo(x - 1, y) != 1 && !OverworldState.NPCPresent(x - 1, y)) {
					x -= 1;
					
				}
				moveLeft = true;
				facing = "Left";
			}
		}
	}

	public void moveRight() throws InterruptedException {
		if (!OverworldState.inDialog) {
			if (!moveRight && !isMoving) {
				if (OverworldState.movMap.canMoveTo(x + 1, y) != 1 && !OverworldState.NPCPresent(x + 1, y)) {
					x += 1;
					
				}
				moveRight = true;
				facing = "Right";
			}
		}
	}

	// functions for changing sprites at intervals i
	public void animateRight(int i) {
		int speed = 2;
		isMoving = true;
		if (i >= 0 && i < speed) {
			currentSprite = grabImage(spriteSheet, 1, 1);
		} else if (i >= speed && i < speed * 2) {
			currentSprite = grabImage(spriteSheet, 1, 2);
		} else if (i >= speed * 2 && i < speed * 3) {
			currentSprite = grabImage(spriteSheet, 1, 3);
		} else if (i >= speed * 3 && i < speed * 4) {
			currentSprite = grabImage(spriteSheet, 1, 4);
		} else if (i >= speed * 4 && i < speed * 5) {
			currentSprite = grabImage(spriteSheet, 1, 5);
		} else if (i >= speed * 5 && i < speed * 6) {
			currentSprite = grabImage(spriteSheet, 1, 6);
		} else if (i >= speed * 6 && i < speed * 7) {
			currentSprite = grabImage(spriteSheet, 1, 7);
		} else {
			moveRight = false;
			rightAnime = 0;
			isMoving = false;
			if(OverworldState.movMap.canMoveTo(x, y) == 2) {
				if(Math.random() * 100 < battlePer)
				{
					Player.isMoving = true;
					BattleState.bs = "omae.wav";
					BattleState.batMus = "SwampBattle.wav";
					BattleState.startSwampBattle();
				}
			}
		}
	}

	public void animateLeft(int i) {
		int speed = 2;
		isMoving = true;
		if (i >= 0 && i < speed) {
			currentSprite = grabImage(spriteSheet, 2, 1);
		} else if (i >= speed && i < speed * 2) {
			currentSprite = grabImage(spriteSheet, 2, 2);
		} else if (i >= speed * 2 && i < speed * 3) {
			currentSprite = grabImage(spriteSheet, 2, 3);
		} else if (i >= speed * 3 && i < speed * 4) {
			currentSprite = grabImage(spriteSheet, 2, 4);
		} else if (i >= speed * 4 && i < speed * 5) {
			currentSprite = grabImage(spriteSheet, 2, 5);
		} else if (i >= speed * 5 && i < speed * 6) {
			currentSprite = grabImage(spriteSheet, 2, 6);
		} else if (i >= speed * 6 && i < speed * 7) {
			currentSprite = grabImage(spriteSheet, 2, 7);
		} else {
			moveLeft = false;
			leftAnime = 0;
			isMoving = false;
			if(OverworldState.movMap.canMoveTo(x, y) == 2) {
				if(Math.random() * 100 < battlePer)
				{
					Player.isMoving = true;
					BattleState.bs = "omae.wav";
					BattleState.batMus = "SwampBattle.wav";
					BattleState.startSwampBattle();
				}
			}
		}
	}

	public void animateDown(int i) {
		int speed = 6;
		if (i >= 0 && i < speed) {
			isMoving = true;
			currentSprite = grabImage(spriteSheet, 3, 1);
		} else if (i >= speed && i < speed * 2) {
			currentSprite = grabImage(spriteSheet, 3, 2);
		} else {
			moveDown = false;
			downAnime = 0;
			isMoving = false;
			if(OverworldState.movMap.canMoveTo(x, y) == 2) {
				if(Math.random() * 100 < battlePer)
				{
					Player.isMoving = true;
					BattleState.bs = "omae.wav";
					BattleState.batMus = "SwampBattle.wav";
					BattleState.startSwampBattle();
				}
			}
		}
	}

	public void animateUp(int i) {
		int speed = 6;
		if (i >= 0 && i < speed) {
			isMoving = true;
			currentSprite = grabImage(spriteSheet, 4, 1);
		} else if (i >= speed && i < speed * 2) {
			currentSprite = grabImage(spriteSheet, 4, 2);
		} else {
			moveUp = false;
			upAnime = 0;
			isMoving = false;
			if(OverworldState.movMap.canMoveTo(x, y) == 2) {
				if(Math.random() * 100 < battlePer)
				{
					Player.isMoving = true;
					BattleState.bs = "omae.wav";
					BattleState.batMus = "SwampBattle.wav";
					BattleState.startSwampBattle();
				}
			}
		}
	}

	public void punchRight(int i) {
		int speed = 5;
		isMoving = true;
		if (i >= 0 && i < speed) {
			currentSprite = grabImage(spriteSheet, 5, 1);
		} else if (i >= speed && i < speed * 2) {
			currentSprite = grabImage(spriteSheet, 5, 2);
		} else if (i >= speed * 2 && i < speed * 3) {
			currentSprite = grabImage(spriteSheet, 5, 3);
		} else if (i >= speed * 3 && i < speed * 4) {
			currentSprite = grabImage(spriteSheet, 5, 4);
		} else {
			punRight = false;
			punRightAnime = 0;
			isMoving = false;
		}
	}

	public void punchLeft(int i) {
		int speed = 9;
		isMoving = true;
		if (i >= 0 && i < speed) {
			currentSprite = grabImage(spriteSheet, 6, 1);
		} else if (i >= speed && i < speed * 2) {
			currentSprite = grabImage(spriteSheet, 6, 2);
		} else if (i >= speed * 2 && i < speed * 3) {
			currentSprite = grabImage(spriteSheet, 6, 3);
		} else if (i >= speed * 3 && i < speed * 4) {
			currentSprite = grabImage(spriteSheet, 6, 4);
		} else {
			punLeft = false;
			punLeftAnime = 0;
			isMoving = false;
		}
	}

	public void animateDie(int i) {
		int tim = 5;
		if (i >= 0 && i < tim) {
			isMoving = true;
			currentSprite = grabImage(spriteSheet, 7, 1);
		} else if (i >= tim && i < tim * 2) {
			currentSprite = grabImage(spriteSheet, 7, 2);
		} else if (i >= tim * 2 && i < tim * 3) {
			currentSprite = grabImage(spriteSheet, 7, 3);
		} else if (i >= tim * 3 && i < tim * 4) {
			currentSprite = grabImage(spriteSheet, 7, 4);
		} else if (i >= tim * 4 && i < tim * 5) {
			currentSprite = grabImage(spriteSheet, 7, 5);
		} else if (i >= tim * 5 && i < tim * 6) {
			currentSprite = grabImage(spriteSheet, 7, 6);
		} else if (i >= tim * 6 && i < tim * 7) {
			currentSprite = grabImage(spriteSheet, 7, 7);
		} else if (i >= tim * 7 && i < tim * 8) {
			currentSprite = grabImage(spriteSheet, 7, 8);
		} else if (i >= tim * 8 && i < tim * 9) {
			currentSprite = grabImage(spriteSheet, 7, 9);
		} else if (i >= tim * 9 && i < tim * 10) {
			currentSprite = grabImage(spriteSheet, 7, 10);
		} else if (i >= tim * 10 && i < tim * 11) {
			currentSprite = grabImage(spriteSheet, 7, 11);
		} else if (i >= tim * 11 && i < tim * 12) {
			currentSprite = grabImage(spriteSheet, 7, 12);
		} else if (i >= tim * 12 && i < tim * 13) {
			currentSprite = grabImage(spriteSheet, 7, 13);
		} else if (i >= tim * 13 && i < tim * 14) {
			currentSprite = grabImage(spriteSheet, 7, 11);
		} else if (i >= tim * 14 && i < tim * 15) {
			currentSprite = grabImage(spriteSheet, 7, 12);
		} else if (i >= tim * 15 && i < tim * 16) {
			currentSprite = grabImage(spriteSheet, 7, 13);
		} else if (i >= tim * 16 && i < tim * 17) {
			currentSprite = grabImage(spriteSheet, 7, 11);
		} else if (i >= tim * 17 && i < tim * 18) {
			currentSprite = grabImage(spriteSheet, 7, 12);
		} else if (i >= tim * 18 && i < tim * 19) {
			currentSprite = grabImage(spriteSheet, 7, 13);
		} else if (i >= tim * 19 && i < tim * 20) {
			currentSprite = grabImage(spriteSheet, 8, 4);
		} 
	}

	public void Dab() {
		if (dabState == false) {
			currentSprite = grabImage(spriteSheet, 8, 2);
			dabState = true;
		} else {
			currentSprite = grabImage(spriteSheet, 8, 3);
			dabState = false;
		}
	}

	public void tick() {
		if (moveUp) {
			animateUp(upAnime);
			upAnime++;
		}

		if (moveDown) {
			animateDown(downAnime);
			downAnime++;
		}

		if (moveLeft) {
			animateLeft(leftAnime);
			leftAnime++;
		}

		if (moveRight) {
			animateRight(rightAnime);
			rightAnime++;
		}

		if (punLeft) {
			punchLeft(punLeftAnime);
			punLeftAnime++;
		}
		
		if (punRight) {
			punchRight(punRightAnime);
			punRightAnime++;
		}

		if (BattleState.shrek.rip) {
			animateDie(dieAnime);
			dieAnime++;
		}
		if(dab) {
			Hitmarker.spam();
		}
		
	}

	/*
	 * draws player
	 */
	public static void render(Graphics g) {
		g.drawImage(currentSprite, Game.WIDTH / 2 - Game.WIDTH / 2 % 49, Game.HEIGHT / 2 - Game.HEIGHT / 2 % 49, null);
	}

	/*
	 * getter and setter functions for the variables x and y
	 */
	public int returnX() {
		return x;
	}

	public int returnY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getFacing() {
		return facing;
	}
}
