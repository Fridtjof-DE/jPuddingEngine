package de.puddingmaster.jpuddingengine;

import de.fridtjof_de.puddingapi.logger;

public class Launcher {
	
	public static long START_TIME = System.currentTimeMillis();
	
	public static void main(String[] args) {
		int height = 720;
		int width = height / 9 * 16;
		
		launchEngine(width, height);
	}
	
	public static void launchEngine(int width, int height) {logger.INFO("Launching game...");
		Game game = new Game(width, height);
		game.start();
	}
}
