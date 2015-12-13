package org.modularmc.game.world;

/**
 * @author Caspar Nor�e Palm
 */
public enum LevelType {
	DEFAULT("default"),
	FLAT("flat"),
	LARGEBIOMES("largeBiomes"),
	AMPLIFIED("amplified"),
	DEFAULT_1_1("default_1_1");
	
	private String type;
	
	private LevelType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return type;
	}
}
