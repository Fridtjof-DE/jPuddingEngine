package jpuddingengine;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Launcher {
	
public static long START_TIME = System.currentTimeMillis();
	
	public static void main(String[] args) {
		String title = Engine.NAME + " - " + Engine.VERSION;
		int height = 720;
		int width = height / 9 * 16;
		boolean fullscreen = true;
		boolean debug = true;
		
		launchEngine(title, width, height, fullscreen, debug);
	}
	
	public static void launchEngine(String title, int width, int height, boolean fullscreen, boolean debug) {
		System.out.println("Launching game...");
		if(fullscreen) {
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			width = gd.getDisplayMode().getWidth();
			height = gd.getDisplayMode().getHeight();
		}
		Engine engineCore = new Engine(title, width, height, fullscreen, debug);
		engineCore.start();
	}
}