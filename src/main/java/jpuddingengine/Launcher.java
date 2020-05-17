package jpuddingengine;

public class Launcher {
	
public static long START_TIME = System.currentTimeMillis();
	
	public static void main(String[] args) {
		int height = 720;
		int width = height / 9 * 16;
		boolean fullscreen = false;
		
		launchEngine(width, height, fullscreen);
	}
	
	public static void launchEngine(int width, int height, boolean fullscreen) {
		System.out.println("Launching game...");
		/*if(fullscreen) {
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			width = gd.getDisplayMode().getWidth();
			height = gd.getDisplayMode().getHeight();
		}*/
		//width = 1920;
		//height = 1080;
		EngineCore engineCore = new EngineCore(width, height, fullscreen);
		engineCore.start();
	}
}