package de.puddingmaster.jpuddingengine.tiles;

import de.puddingmaster.jpuddingengine.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.ROCK_TILE, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
