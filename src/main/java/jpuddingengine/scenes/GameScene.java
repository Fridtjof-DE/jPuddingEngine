package jpuddingengine.scenes;

import java.awt.Graphics;

import jpuddingengine.Handler;
import jpuddingengine.entites.Player;
import jpuddingengine.entites.Tree;
import jpuddingengine.gfx.Assets;
import jpuddingengine.tiles.Tile;
import jpuddingengine.worlds.World;

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
