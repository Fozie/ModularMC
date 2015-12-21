package org.modularmc.game;

import java.util.concurrent.CopyOnWriteArrayList;

import org.modularmc.Server;
import org.modularmc.game.world.Dimension;
import org.modularmc.game.world.World;
import org.modularmc.game.world.generate.SimpleWorldGenerator;

/**
 * @author Caspar Norée Palm
 */
public class WorldManager {
	
	private CopyOnWriteArrayList<World> loadedWorlds;
	
	private World spawnWorld;
	
	private final Server server;
	
	public WorldManager(Server server) {
		this.server = server;
		this.loadedWorlds = new CopyOnWriteArrayList<>();
		
		spawnWorld = new World(server, new SimpleWorldGenerator(), "world", Dimension.OVERWORLD);	
	}
	
	
	public World getSpawnWorld() {
		return spawnWorld;
	}
	
	public void addWorld(World world) {
		loadedWorlds.addIfAbsent(world);
	}
	
	public void tick() {
		for(World w : loadedWorlds) {
			w.tick();
		}
	}
	
}
