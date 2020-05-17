package jpuddingengine.scenes;

import java.awt.Graphics;

import jpuddingengine.Engine;
import jpuddingengine.Handler;

public abstract class Scenes {
	
	private static Scenes currentState = null;
	
	public static void setState(Scenes state) {
		currentState = state;
	}
	
	public static Scenes getState() {
		return currentState;
	}
	
	//CLASS
	protected Handler handler;
	
	public Scenes(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

}
