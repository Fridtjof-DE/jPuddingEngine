package jpuddingengine.entites;

import java.awt.Color;
import java.awt.Graphics;

import jpuddingengine.Handler;
import jpuddingengine.gfx.Assets;
import jpuddingengine.items.Item;
import jpuddingengine.tiles.Tile;

public class Tree extends StaticEntity {

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE * 2);
		
		//Specifys the hitbox, default would be the hole sprite
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.PURPLE_TILE, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		//Draw hitbox
		g.setColor(Color.RED);
		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
}
