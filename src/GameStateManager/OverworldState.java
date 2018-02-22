package GameStateManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;

import FileSystem.MapRetrevial;
import Maps.Link;
import Maps.MovementMap;
import NPC.NPC;
import Player.Player;
import Sounds.Music;
import TextMap.TextMap;
import java.io.*;

public class OverworldState extends GameState {
	/*
	 * GameState that loads the text base view that is used mostly for testing
	 */

	public static MovementMap movMap = new MovementMap();
	public static TextMap textMap = new TextMap();
	public static Player player = new Player();
	public static ArrayList<Link> links = new ArrayList<Link>();
	public BufferedImage map;
	BufferedImage subMap;
<<<<<<< HEAD
	public static ArrayList<NPC> npcs = new ArrayList<NPC>();
	static String dialog;
	static ArrayList<String> lines = new ArrayList<String>();
	ArrayList<String> displayText = new ArrayList<String>();
	public static boolean inDialog = false;
	static int currentLine = 0;
	Font font = new Font("Courier New", Font.PLAIN, 30);
	Font bold = new Font("Courier New", Font.BOLD, 30);
	static String npcName;
=======
	public static String swampMusic = "Music\\All_Star_Chip.wav";
>>>>>>> master

	/*
	 * loading and initializing objects in OverworldState
	 */
	public OverworldState() {

		try {
			movMap.loadMap("World1");
			textMap.loadMap("test");
			MapRetrevial.readNPC("World1");
			map = ImageIO.read(getClass().getResource("/Swamp_Map.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		movMap.printMap();

	}

	public static void stateOverworldState() {
<<<<<<< HEAD
		Music.startSound("Music\\All_Star_Chip.wav", true);
=======
		Music.startSound(swampMusic, true); 
>>>>>>> master
	}

	public static boolean NPCPresent(int x, int y) {
		for (NPC npc : npcs) {
			if (npc.x == x && npc.y == y) {
				return true;
			}
		}
		return false;
	}

	public static void interact() {
		if (inDialog) {
			currentLine += 3;
			if (currentLine >= lines.size()) {
				inDialog = false;
			}

		} else {
			for (NPC npc : npcs) {
				if (player.getFacing().equals("Up")) {
					if (npc.x == player.returnX() && npc.y == player.returnY() - 1) {
						dialog = npc.getText();
						inDialog = true;
						calculateLines();
						npcName = npc.name;
					}
				} else if (player.getFacing().equals("Down")) {
					if (npc.x == player.returnX() && npc.y == player.returnY() + 1) {
						dialog = npc.getText();
						inDialog = true;
						calculateLines();
						npcName = npc.name;
					}
				} else if (player.getFacing().equals("Left")) {
					if (npc.x == player.returnX() - 1 && npc.y == player.returnY()) {
						dialog = npc.getText();
						inDialog = true;
						calculateLines();
						npcName = npc.name;
					}
				} else if (player.getFacing().equals("Right")) {
					if (npc.x == player.returnX() + 1 && npc.y == player.returnY()) {
						dialog = npc.getText();
						inDialog = true;
						calculateLines();
						npcName = npc.name;
					}
				}
			}
		}
	}

	public static void calculateLines() {
		currentLine = 0;
		lines.clear();
		int maxPer = 58;
		int n = 0;
		String line;
		do {
			if (n + maxPer > dialog.length()) {
				line = dialog.substring(n, dialog.length());
				if(line.substring(0, 1).equals(" ")) {
					line = line.substring(1, line.length());
				}
				lines.add(line);
				n = dialog.length();
			} else {
				int i = maxPer;
				while (!dialog.substring(i, i + 1).equals(" ")) {
					i--;
				}
				line = dialog.substring(n, n + i);
				if(line.substring(0, 1).equals(" ")) {
					line = line.substring(1, line.length());
				}
				lines.add(line);
				n += i;
			}
		} while (n < dialog.length());
	}

	/*
	 * Passing down the tick() function even more to any object that needs to update
	 */
	public void tick() {
		player.tick();

		try {
			for (Link link : links) {
				link.tick();
			}
		} catch (ConcurrentModificationException e) {

		}
	}

	/*
	 * Passes down the render function
	 */
	public void render(Graphics g) {
		// textMap.render(g);
		subMap = map.getSubimage(1000 + player.returnX() * Game.PIXSIZE - 11 * Game.PIXSIZE,
				1000 + player.returnY() * Game.PIXSIZE - 6 * Game.PIXSIZE, Game.WIDTH + 30, Game.HEIGHT + 30);
		g.drawImage(subMap, 0, 0, null);
		player.render(g);
		for (NPC npc : npcs) {
			npc.render(g);
		}
		if (inDialog) {
			g.setFont(bold);
			g.setColor(Color.WHITE);
			g.fillRect(0, Game.HEIGHT - Game.PIXSIZE * 3, Game.WIDTH + 10, Game.HEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(0, Game.HEIGHT - Game.PIXSIZE * 3, Game.WIDTH + 10, Game.HEIGHT);
			g.drawString(npcName, 20 ,Game.HEIGHT - Game.PIXSIZE * 3 + 40);
			g.setFont(font);
			for (int i = currentLine; i < lines.size() && i < currentLine + 3; i++) {
				g.drawString(lines.get(i), 20, Game.HEIGHT - Game.PIXSIZE * 3 + 70 + 30 * (i - currentLine));
			}
		}

	}
}
