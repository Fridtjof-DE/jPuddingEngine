package de.puddingmaster.jpuddingengine;

import java.awt.Dimension;

import de.puddingmaster.jpuddingengine.display.Display;
import de.puddingmaster.jpuddingengine.gfx.GameCamera;
import de.puddingmaster.jpuddingengine.input.KeyManager;
import de.puddingmaster.jpuddingengine.input.MouseManager;
import de.puddingmaster.jpuddingengine.networking.GameClient;
import de.puddingmaster.jpuddingengine.networking.GameServer;
import de.puddingmaster.jpuddingengine.worlds.World;

public class Handler {

	private Game game;
	private World world;
	//public GameClient socketClient;
	//public GameServer socketServer;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public GameCamera getGameCamera()  {
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
