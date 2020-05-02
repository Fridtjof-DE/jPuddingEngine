package de.puddingmaster.jpuddingengine;

import java.util.logging.Logger;

import de.fridtjof_de.puddingapi.logger;
import de.puddingmaster.jpuddingengine.display.Display;

public class Launcher {
	
	public static long START_TIME = System.currentTimeMillis();
	
	public static void main(String[] args) {
		
		int height = 720;
		int width = height / 9 * 16;
		
		logger.INFO("Launching game...");
		Game game = new Game(width, height);
		game.start();
	}
}
