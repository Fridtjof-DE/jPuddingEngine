package de.puddingmaster.jpuddingengine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import de.fridtjof_de.puddingapi.logger;
import de.puddingmaster.jpuddingengine.display.Display;
import de.puddingmaster.jpuddingengine.gfx.Assets;
import de.puddingmaster.jpuddingengine.gfx.GameCamera;
import de.puddingmaster.jpuddingengine.gfx.ImageLoader;
import de.puddingmaster.jpuddingengine.gfx.SpriteSheet;
import de.puddingmaster.jpuddingengine.input.KeyManager;
import de.puddingmaster.jpuddingengine.input.MouseManager;
import de.puddingmaster.jpuddingengine.networking.GameClient;
import de.puddingmaster.jpuddingengine.networking.GameServer;
import de.puddingmaster.jpuddingengine.scenes.GameScene;
import de.puddingmaster.jpuddingengine.scenes.MenuScene;
import de.puddingmaster.jpuddingengine.scenes.Scenes;

public class Game implements Runnable{
	
	private Display display;
	private int width, height;
	public String version = "Alpha #1";
	public String name = "PuddingEngine";
	public String title = name + " " + version;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	public MenuScene menuScene;
	public GameScene gameScene;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	//Networking
	public GameClient socketClient;
	private GameServer socketServer;
	
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		logger.INFO("Initialisation...");
		
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		
		gameCamera = new GameCamera(handler, 0, 0);
		
		menuScene = new MenuScene(handler);		
		gameScene = new GameScene(handler);
		Scenes.setState(menuScene);
		
		long boottime = System.currentTimeMillis() - Launcher.START_TIME;
		
		socketClient.sendData("ping".getBytes());
		
		logger.INFO("Game successfully started in " + boottime + "ms!");
	}
	
	private void tick() {
		keyManager.tick();
		
		if(Scenes.getState() != null) {
			Scenes.getState().tick();
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
		
		
		if(Scenes.getState() != null) {
			Scenes.getState().render(g);
		}

		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int tps_limit = 60;
		int fps_limit = 60;
		double timePerTick = 1000000000 / tps_limit;
		double timePerFrame = 1000000000 / fps_limit;
		double delta_tps = 0;
		double delta_fps = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		int frames = 0;
		
		
		while(running) {
			now = System.nanoTime();
			delta_tps += (now - lastTime) / timePerTick;
			delta_fps += (now - lastTime) / timePerFrame;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta_tps >= 1) {
				tick();
				ticks++;
				delta_tps--;
			}
			
			if(delta_fps >= 1) {
				render();
				frames++;
				delta_fps--;
			}
			
			if(timer >= 1000000000) {
				Display.updateTitle(title + " - TPS: " + ticks + ", FPS: " + frames);
				ticks = 0;
				frames = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if(running) {
			return;
		} else {
			
			//Networking
			if(JOptionPane.showConfirmDialog(null, "Do you want to run the server") == 0) {
				socketServer = new GameServer(this);
				logger.INFO("Starting server thread...");
				socketServer.setName("server thread");
				socketServer.start();
			}
			socketClient = new GameClient(this, "localhost");
			logger.INFO("Starting client thread...");
			socketClient.setName("client thread");
			socketClient.start();
			
			
			
			running = true;
			thread = new Thread(this);
			thread.setName("Main thread");
			logger.INFO("Starting main thread...");
			thread.start();
		}
	}
	
	public synchronized void stop() {
		logger.INFO("Stopping game...");
		if(!running) {
			return;
		} else {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
