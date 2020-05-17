package jpuddingengine.entites;

import jpuddingengine.Handler;
import jpuddingengine.tiles.Tile;

public abstract class Creature extends Entity {
	
	public static final float DEFAULT_SPEED = 3.0F;
	public static final int DEFAULT_CREATE_WDTH = 32;
	public static final int DEFAULT_CREATE_HEIGHT = 32;

	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f)) {
			moveX();
		}
		if(!checkEntityCollisions(0f, yMove)) {
			moveY();
		}
	}
	
	public void moveX() {
		if(xMove > 0) { //Moving right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_SIZE;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_SIZE) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_SIZE)) {
				x += xMove;
			} else {
				 x = tx * Tile.TILE_SIZE - bounds.x - bounds.width - 1;
			}
			
		} else if (xMove < 0) { //Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_SIZE;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_SIZE) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_SIZE)) {
				x += xMove;
			} else {
				 x = tx * Tile.TILE_SIZE + Tile.TILE_SIZE - bounds.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) { //Moving up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_SIZE;		
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_SIZE, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_SIZE, ty)) {
				y += yMove;
			} else {
				 y = ty * Tile.TILE_SIZE + Tile.TILE_SIZE - bounds.y;
			}
			
		} else if(yMove > 0) { //Moving down
			int ty = (int) (y + yMove + bounds.y + bounds.width) / Tile.TILE_SIZE;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_SIZE, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_SIZE, ty)) {
				y += yMove;
			} else {
				 y = ty * Tile.TILE_SIZE - bounds.y - bounds.height - 1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//Getters & setters
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public static float getDefaultSpeed() {
		return DEFAULT_SPEED;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
}
