package jpuddingengine;

public class Handler {

	private EngineCore engineCore;
	
	public Handler(EngineCore engineCore) {
		this.engineCore = engineCore;
	}
	
	public int getWidth() {
		return engineCore.getWidth();
	}
	
	public int getHeight() {
		return engineCore.getHeight();
	}
}