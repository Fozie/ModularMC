package org.modularmc.game.world;

import java.util.Arrays;

import org.modularmc.utils.NibbleUtils;

/**
 * @author Caspar Norée Palm
 */
public class ChunkSection {
	private final Chunk chunk;
	
	private final int y; // Ranging between 0 - 15
	
	private char[] blockTypes;
	private byte[] blockLightning; // Nibblearray, each byte contains blocklightning for 2 blocks, 4 bits each
	private byte[] skyLightning; // Nibblearray, each byte contains skylightning for 2 blocks, 4 bits each

	
	private int count; // Number of blocks in this section (Not air)

	public ChunkSection(Chunk chunk, int y) {
		super();
		this.chunk = chunk;
		this.y = y;
		
		blockTypes = new char[8192];
		
		blockLightning = new byte[2048];
		skyLightning = new byte[2048];
	
		Arrays.fill(skyLightning, (byte) -1);
	}

	public int blockIndex(int x, int y, int z) {
        return ((y & 0xf) << 8) | (z << 4) | x;
    }

	public int getY() {
		return y;
	}

	public char[] blockTypes() {
		return blockTypes;
	}
	
	public byte[] blockLightning() {
		return blockLightning;
	}
	
	public byte[] skyLightning() {
		return skyLightning;
	}
    
	public void setBlockLightning(int index, int val) {
		NibbleUtils.set(blockLightning, index, (byte) val);
	}
	
	public void setSkyLightning(int index, int val) {
		NibbleUtils.set(skyLightning, index, (byte) val);
	}
	
	public int getBlockLightning(int index) {
		return NibbleUtils.get(blockLightning, index);
	}
	
	public int getSkyLightning(int index) {
		return NibbleUtils.get(skyLightning, index);
	}
	
	public void setBlockType(int x, int y, int z, char val) {
		blockTypes[blockIndex(x,y,z)] = val;
	}
}
