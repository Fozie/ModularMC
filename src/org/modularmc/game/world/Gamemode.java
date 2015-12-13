package org.modularmc.game.world;

/**
 * @author Caspar Norée Palm
 */
public enum Gamemode {
	SURVIVAL(0),
	CREATIVE(1),
	ADVENTURE(2),
	SPECTATOR(3);
	
	final int id;
	
	private Gamemode(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
}
