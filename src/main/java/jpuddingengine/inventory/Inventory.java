package jpuddingengine.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import jpuddingengine.Handler;
import jpuddingengine.items.Item;

public class Inventory {
	
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;

	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}
	
	public void tick() {
		if(!active) {
			return;
		}
		
		System.out.println("Inventory: ");
		for(Item i : inventoryItems) {
			System.out.println(i.getCount() + " times " + i.getName());
		}
	}
	
	public void render(Graphics g) {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			active = !active;
		}
		if(!active) {
			return;
		}
		
		System.out.println("Inventory");
	}
	
	//InventoryItems
	
	public void addItems(Item item) {
		for(Item i : inventoryItems) {
			if(i.getID() == item.getID()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	
	//Getters and setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
