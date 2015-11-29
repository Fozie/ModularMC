package org.modularmc.game.world;

/**
 * @author Caspar Norée Palm
 */
public class ChunkSection {
	private final Chunk chunk;
	
	private final int y; // Ranging between 0 - 15
	
	private char[] blockTypes;
	private byte[] lightning; // Nibblearray, each byte contains both the skylight and the blocklight. 4 bits for skylight, 4 bits for blocklight.
	
	private int count; // Number of blocks in this section (Not air)

	public ChunkSection(Chunk chunk, int y) {
		super();
		this.chunk = chunk;
		this.y = y;
	}

	public int blockIndex(int x, int y, int z) {
        return ((y & 0xf) << 8) | (z << 4) | x;
    }

	public int getY() {
		return y;
	}

	protected int writeBlockData(byte[] data, int pos) {
		for (final char type : blockTypes) {
			data[pos++] = (byte) (type & 0xff);
			data[pos++] = (byte) (type >> 8);
		} 
		
		return pos;
	}

	/**
	 * @param data
	 * @param pos
	 * @return
	 */
	public int writeLightData(byte[] data, int pos) {
		return 0;
	}
    
}
