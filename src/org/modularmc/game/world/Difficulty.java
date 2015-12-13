package org.modularmc.game.world;

/**
 * @author Caspar Nor�e Palm
 */
public enum Difficulty {
	PEACEFUL(0),
	EASY(1),
	NORMAL(2),
	HARD(3);

	//TODO: Hardcore :)
	
	private byte id;
	
	private Difficulty(int id) {
		this.id = (byte) id;
	}
	
	public byte getID() {
		return id;
	}
}
