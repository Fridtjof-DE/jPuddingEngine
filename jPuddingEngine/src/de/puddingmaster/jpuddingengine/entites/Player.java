package de.puddingmaster.jpuddingengine.entites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.puddingmaster.jpuddingengine.Handler;
import de.puddingmaster.jpuddingengine.gfx.Animation;
import de.puddingmaster.jpuddingengine.gfx.Assets;
import de.puddingmaster.jpuddingengine.inventory.Inventory;

public class Player extends Creature {
	
	//Animation
	private Animation animDown, animUp, animLeft, animRight, animIdle;
	//Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	//Iventory
	private Inventory inventory;
	//Server
	private String username;

	public Player(Handler handler, float x, float y, String username) {
		super(handler, x, y, DEFAULT_CREATE_WDTH, DEFAULT_CREATE_HEIGHT);
		this.username = username;
		
		//Specifys the hitbox, default would be the hole sprite
		/*bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;*/
		
		//Animation
		animDown = new Animation(500, Assets.PLAYER_DOWN);
		animUp = new Animation(500, Assets.PLAYER_UP);
		animLeft = new Animation(500, Assets.PLAYER_LEFT);
		animRight = new Animation(500, Assets.PLAYER_RIGHT);
		animIdle = new Animation(500, Assets.PLAYER_IDLE);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		//Animation
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animIdle.tick();
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		//Attack
		checkAttacks();
		//Inventory
		inventory.tick();
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) {
			return;
		}
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		} else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		} else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else if(handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}
	}
	
	@Override
	public void die() {
		System.out.println("Du bist gestorben!");
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up) {
			yMove = -speed;
		}
		
		if(handler.getKeyManager().down) {
			yMove = speed;
		}
		
		if(handler.getKeyManager().left) {
			xMove = -speed;
		}
		
		if(handler.getKeyManager().right) {
			xMove = speed;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		inventory.render(g);
		
		//Draw hitbox
		g.setColor(Color.RED);
		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
		
		//Username
		if(username == null) {
			username = "New User";
		}
		if(username != null) {
			g.drawString(username, (int) (x - handler.getGameCamera().getxOffset()) - (username.length() / 2 * 3), (int) (y - handler.getGameCamera().getyOffset()) - 10);
		}
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		} else {
			return animIdle.getCurrentFrame();
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
