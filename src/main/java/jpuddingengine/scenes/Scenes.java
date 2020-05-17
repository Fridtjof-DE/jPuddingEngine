package jpuddingengine.scenes;

import java.awt.Graphics;

import jpuddingengine.Handler;

public abstract class Scenes {
	
	protected Handler handler;
	
	private static Scenes currentState = null;
	
	public Scenes(Handler handler) {
		this.handler = handler;
	}
	
	public static void setState(Scenes state) {
		currentState = state;
	}
	
	public static Scenes getState() {
		return currentState;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
}