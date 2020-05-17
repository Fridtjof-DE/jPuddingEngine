package jpuddingengine.scenes;

import java.awt.Graphics;

import jpuddingengine.Handler;
import jpuddingengine.gfx.Assets;
import jpuddingengine.ui.ClickListener;
import jpuddingengine.ui.UIImageButton;
import jpuddingengine.ui.UIManager;

public class MenuScene extends Scenes {
	
	private UIManager uiManager;

	public MenuScene(final Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.PLAYER_DOWN, new ClickListener(){

			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				Scenes.setState(handler.getEngine().gameScene);
			}}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
