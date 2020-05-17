package jpuddingengine;

public class Launcher {
	
	public static long START_TIME = System.currentTimeMillis();
	
	public static void main(String[] args) {
		int height = 720;
		int width = height / 9 * 16;
		
		launchEngine(width, height);
	}
	
	public static void launchEngine(int width, int height) {System.out.println("Launching engine...");
		Engine engine = new Engine(width, height);
		engine.start();
	}
}
