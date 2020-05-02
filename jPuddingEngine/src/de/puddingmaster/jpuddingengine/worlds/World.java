package de.puddingmaster.jpuddingengine.worlds;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import de.fridtjof_de.puddingapi.logger;
import de.puddingmaster.jpuddingengine.Game;
import de.puddingmaster.jpuddingengine.Handler;
import de.puddingmaster.jpuddingengine.display.Display;
import de.puddingmaster.jpuddingengine.entites.EntityManager;
import de.puddingmaster.jpuddingengine.entites.Player;
import de.puddingmaster.jpuddingengine.entites.Tree;
import de.puddingmaster.jpuddingengine.items.ItemManager;
import de.puddingmaster.jpuddingengine.networking.GameClient;
import de.puddingmaster.jpuddingengine.networking.GameServer;
import de.puddingmaster.jpuddingengine.tiles.Tile;
import de.puddingmaster.jpuddingengine.utils.Utils;

public class World {

	private Game game;
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int [][] tiles;
	
	//Entities
	private EntityManager entityManager;
	//Items
	private ItemManager itemManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100, JOptionPane.showInputDialog("Please enter a username", "Username")));
		
		entityManager.addEntity(new Tree(handler, 250, 450));
		itemManager = new ItemManager(handler);
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_SIZE);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_SIZE + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_SIZE);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_SIZE + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_SIZE - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILE_SIZE - handler.getGameCamera().getyOffset())); 
			}
		}
		
		/*for(int y = 0; y < height; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_SIZE - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILE_SIZE - handler.getGameCamera().getyOffset())); 
			}
		}*/
		//Items
		itemManager.render(g);
		//Entites
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.purpleTile;
		}
		return t;
	}
	
	private void loadWorld(String path) {
		logger.INFO("Loading world...");
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	//Getters and setters
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
}