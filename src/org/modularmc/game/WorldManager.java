package org.modularmc.game;

import java.util.concurrent.CopyOnWriteArrayList;

import org.modularmc.Server;
import org.modularmc.game.world.World;

/**
 * @author Caspar Norée Palm
 */
public class WorldManager {
	
	private CopyOnWriteArrayList<World> loadedWorlds;
	
	private final Server server;
	
	public WorldManager(Server server) {
		this.server = server;
		this.loadedWorlds = new CopyOnWriteArrayList<>();
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
