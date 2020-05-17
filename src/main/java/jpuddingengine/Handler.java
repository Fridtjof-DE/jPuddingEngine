package jpuddingengine;

import jpuddingengine.gfx.GameCamera;
import jpuddingengine.input.KeyManager;
import jpuddingengine.input.MouseManager;
import jpuddingengine.worlds.World;

public class Handler {

	private Engine engine;
	private World world;
	//public EngineClient socketClient;
	//public EngineServer socketServer;
	
	public Handler(Engine engine) {
		this.engine = engine;
	}
	
	public GameCamera getGameCamera()  {
		return engine.getGameCamera();
	}
	
	public KeyManager getKeyManager() {
		return engine.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return engine.getMouseManager();
	}
	
	public int getWidth() {
		return engine.getWidth();
	}
	
	public int getHeight() {
		return engine.getHeight();
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
