package de.puddingmaster.jpuddingengine.scenes;

import java.awt.Color;
import java.awt.Graphics;

import de.fridtjof_de.puddingapi.logger;
import de.puddingmaster.jpuddingengine.Game;
import de.puddingmaster.jpuddingengine.Handler;
import de.puddingmaster.jpuddingengine.gfx.Assets;
import de.puddingmaster.jpuddingengine.ui.ClickListener;
import de.puddingmaster.jpuddingengine.ui.UIImageButton;
import de.puddingmaster.jpuddingengine.ui.UIManager;

public class MenuScene extends Scenes {
	
	private UIManager uiManager;

	public MenuScene(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.PLAYER_DOWN, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				Scenes.setState(handler.getGame().gameScene);
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
