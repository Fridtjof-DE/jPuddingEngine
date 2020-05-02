package de.puddingmaster.jpuddingengine.scenes;

import java.awt.Graphics;

import de.puddingmaster.jpuddingengine.Handler;
import de.puddingmaster.jpuddingengine.entites.Player;
import de.puddingmaster.jpuddingengine.entites.Tree;
import de.puddingmaster.jpuddingengine.gfx.Assets;
import de.puddingmaster.jpuddingengine.tiles.Tile;
import de.puddingmaster.jpuddingengine.worlds.World;

public class GameScene extends Scenes {
	
	private World world;

	public GameScene(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.save");
		handler.setWorld(world);
		
		//Keine Ahnung ob das noch was macht? handler.getGameCamera().move(100, 200);
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}
}
