package org.modularmc.game.world;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.modularmc.Server;
import org.modularmc.game.world.generate.WorldGenerator;
import org.modularmc.utils.ArrayCollection;

import com.sun.istack.internal.Nullable;

/**
 * @author Caspar Norée Palm
 */
public class World {

	private final Server server;
	
	private final String name;
	
	private Map<Long, Chunk> chunkMap;
	private CopyOnWriteArrayList<Chunk> chunks;
	
	private Dimension dimensions;
	
	private int time; // In ticks ranging between 0 - 24000 (1s = 20ticks)
	private long age; // Age of world in ticks
	
	private final WorldGenerator generator;
	
	public World(Server server, WorldGenerator generator, String name, Dimension dimension) {
		this.server = server;
		this.name = name;
		this.generator = generator;
		this.chunkMap = new ConcurrentHashMap<>(100);
		this.chunks = new CopyOnWriteArrayList<>();
		this.dimensions = dimension;
	}
	
	@Nullable
	public Chunk getChunk(int x, int y) {
		return chunkMap.get(encodeChunkPosition(x,y));
	}
	
	public static long encodeChunkPosition(final int x, final int y) {
		return (long) x << 32 | y & 0xFFFFFFFFL;
	}
	
	public void addChunk(final Chunk... chunks) {
		for(Chunk c : this.chunks) {
				chunkMap.put(encodeChunkPosition(c.getX(), c.getZ()), c);
		}
		
		this.chunks.addAllAbsent(new ArrayCollection<Chunk>(chunks));
	}
	
	public Chunk getGetAndGenerate(int x, int y) {
		if(!chunkMap.containsKey(encodeChunkPosition(x,y)))
			addChunk(generator.generateChunk(this, x, y));
		return null;
		
		
	}

	private void increaseTime() {
		if(++time > 24000)
			time = 0;
		
		age++;
		
		//TODO: Send time packet to all players in the world!
	}
	
	public void tick() {
		increaseTime();
		for(Chunk c : chunks) {
			if(!chunkMap.containsKey(encodeChunkPosition(c.getX(), c.getZ())))
				chunkMap.put(encodeChunkPosition(c.getX(), c.getZ()), c);
			c.tick();
		}
	}
}
