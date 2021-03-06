package FileSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import GameStateManager.Game;
import GameStateManager.OverworldState;
import Sounds.Music;

public class Save {

	
	/*
	 * Saves all the values of the overworld state to a file 
	 * 
	 * Currently only the x and y position of the player
	 */
	public static void save() {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("Save\\save.txt"));
			write.write(OverworldState.player.returnX() + "");
			write.newLine();
			write.write(OverworldState.player.returnY()+ "");
			write.newLine();
			write.write(Game.gameStateManager.overworld.linksLocation);
			write.newLine();
			write.write(Game.gameStateManager.overworld.mapLocation);
			write.newLine();
			write.write(Game.gameStateManager.overworld.song);
			write.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Loads all the values in the overworld state to a file
	 * 
	 * currently only saves the x and y values of the player
	 */
	public static void load() throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new FileReader("Save\\save.txt"));
		OverworldState.player.setX(Integer.parseInt(in.readLine()));
		OverworldState.player.setY(Integer.parseInt(in.readLine()));
		String s = in.readLine();
		Game.gameStateManager.overworld.linksLocation = s;
		Game.gameStateManager.overworld.movMap.loadMap(s);
		MapRetrevial.retiveLinks(Game.gameStateManager.overworld.links, s);
		MapRetrevial.readNPC(s);
		String map = in.readLine();
		Game.gameStateManager.overworld.mapLocation = map;
		Game.gameStateManager.overworld.setMap(map);
		Game.gameStateManager.overworld.song = in.readLine();
		MapRetrevial.readEncounter(s);
	}
}
