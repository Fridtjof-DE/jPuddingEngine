package jpuddingengine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import jpuddingengine.gfx.Display;

public class EngineCore implements Runnable {

	private int width, height;
	private boolean fullscreen;
	public static final String VERSION = "0.1.0-Snapshot";
	public static final String NAME = "jPuddingEngine";
	public String title = NAME + " " + VERSION;
	
	private boolean running = false;
	private Thread thread;
	
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	private Handler handler;
	
	public EngineCore(int width, int height, boolean fullscreen) {
		this.width = width;
		this.height = height;
		this.fullscreen = fullscreen;
	}
	
	public void run() {
		init();
		
		int tps_limit = 60;
		int fps_limit = 60000;
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
	
	private void init() {
		System.out.println("Initialisation...");
		display = new Display(title, width, height, fullscreen);

		//handler = new Handler(this);
		
		long boottime = System.currentTimeMillis() - Launcher.START_TIME;
		System.out.println("Game successfully started in " + boottime + "ms!");
	}

	private void tick() {
		
	}
	
	public void render() {
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
		
		
		//if(Scenes.getState() != null) {
		//	Scenes.getState().render(g);
		//}

		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public synchronized void start() {
		if(running) {
			return;
		} else {
			running = true;
			thread = new Thread(this);
			thread.setName("Main thread");
			System.out.println("Starting main thread...");
			thread.start();
		}
	}
	
	public synchronized void stop() {
		System.out.println("Stopping game...");
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
