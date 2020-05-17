package jpuddingengine.scenes;

import java.awt.Color;
import java.awt.Graphics;

import jpuddingengine.Handler;

public class DefaultScene extends Scenes {

	public DefaultScene(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(100, 100, handler.getWidth() + 100, handler.getHeight() + 100);
	}
}
