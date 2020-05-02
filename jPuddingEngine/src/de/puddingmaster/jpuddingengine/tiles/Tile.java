package de.puddingmaster.jpuddingengine.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(1);
	public static Tile rockTile = new RockTile(2);
	public static Tile purpleTile = new PurpleTile(3);
	
	//CLASS
	
	public static final int TILE_SIZE = 64;
	
	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_SIZE, TILE_SIZE, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getID() {
		return id;
	}
}
