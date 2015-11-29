package org.modularmc.game.world;

/**
 * @author Caspar Norée Palm
 */
public class Chunk {
	private final int x, z;
	
	private ChunkSection[] sections;
	
	public Chunk(final int x, final int z) {
		this.x = x;
		this.z = z;
		sections = new ChunkSection[16];
	}
	
	private final ChunkSection getSection(int y) {
		return sections[y >> 4];
	}
	
	public int countSections() {
		int v = 0;
		for(final ChunkSection s : sections)
			if(s != null)
				++v;
		return v;
	}
	
	public short getSectionBitMap() {
		short r = 0;
		for (final ChunkSection s : sections)
			if (s != null)
				r |= 1 << s.getY();
		return r;
	}
	
	public byte[] getData(boolean includeMeta, boolean skylight, boolean biomes) {
		int dataSize = 0;
		
		if(includeMeta) 
			dataSize += 4 + 4 + 2 + 1 + 1; // Two ints (X,Z), One short (SecBitmap), Two booleans (continuos, skylight)
		
		dataSize += ((16 * 16 * 16) * 3) * countSections(); // A short for every block in every section
		
		
		byte[] data = new byte[dataSize];
		
		int pos = 0;
		
		for (final ChunkSection s : sections)
			if (s != null)
				pos = s.writeBlockData(data, pos);
		
		for (final ChunkSection s : sections)
			if (s != null)
				pos = s.writeLightData(data, pos);
		
		
		
		return data;
		
	}
}
