package org.modularmc.game.world;

import java.util.HashMap;
import java.util.Map;

import org.modularmc.server.Server;

/**
 * @author Caspar Norée Palm
 */
public class World {

	private final Server server;
	
	private final String name;
	
	private Map<Long, Chunk> loadedChunks;

	private int time; // In ticks ranging between 0 - 24000 (1s = 20ticks)
	private long age; // Age of world in ticks
	
	public World(Server server, String name) {
		this.server = server;
		this.name = name;
		this.loadedChunks = new HashMap<>();
	}
	
	
	public static long encodeChunkPosition(final int x, final int y) {
		return (long) x << 32 | y & 0xFFFFFFFFL;
	}
}
