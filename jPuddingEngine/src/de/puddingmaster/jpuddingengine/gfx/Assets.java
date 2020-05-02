package de.puddingmaster.jpuddingengine.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int GRID = 16;
	
	public static BufferedImage GRASS_TILE, PLAYER, ROCK_TILE, PURPLE_TILE;
	public static BufferedImage[] PLAYER_DOWN, PLAYER_UP, PLAYER_LEFT, PLAYER_RIGHT, PLAYER_IDLE;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile_map.png"));
		
		PLAYER_DOWN = new BufferedImage[2];
		PLAYER_DOWN[0] = sheet.crop(GRID * 2, 0, GRID, GRID);
		PLAYER_DOWN[1] = sheet.crop(GRID * 3, 0, GRID, GRID);
		PLAYER_UP = new BufferedImage[2];
		PLAYER_UP[0] = sheet.crop(GRID * 4, GRID, GRID, GRID);
		PLAYER_UP[1] = sheet.crop(GRID * 5, GRID, GRID, GRID);
		PLAYER_LEFT = new BufferedImage[2];
		PLAYER_LEFT[0] = sheet.crop(GRID * 4, 0, GRID, GRID);
		PLAYER_LEFT[1] = sheet.crop(GRID * 5, 0, GRID, GRID);
		PLAYER_RIGHT = new BufferedImage[2];
		PLAYER_RIGHT[0] = sheet.crop(GRID * 2, GRID, GRID, GRID);
		PLAYER_RIGHT[1] = sheet.crop(GRID * 3, GRID, GRID, GRID);
		PLAYER_IDLE = new BufferedImage[2];
		PLAYER_IDLE[0] = sheet.crop(GRID, 0, GRID, GRID);
		PLAYER_IDLE[1] = sheet.crop(GRID, 0, GRID, GRID);
		
		
		GRASS_TILE = sheet.crop(0, 0, GRID, GRID);
		PLAYER = sheet.crop(GRID, 0, GRID, GRID);
		ROCK_TILE = sheet.crop(0, GRID, GRID, GRID);
		PURPLE_TILE = sheet.crop(GRID, GRID, GRID, GRID);
	}
}
