package org.modularmc.game.world;

/**
 * @author Caspar Norée Palm
 */
public enum Dimension {
	OVERWORLD(0),
	NETHER(-1),
	END(1);
	
	private byte id;
	
	private Dimension(int id) {
		this.id = (byte) id;
	}
	
	public byte getID() {
		return id;
	}
}
