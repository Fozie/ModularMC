package org.modularmc.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Caspar Norée Palm
 */
public class PlayerManager {
	private CopyOnWriteArrayList<Player> players;
	private ConcurrentHashMap<UUID, Player> playerUUIDMap;
	
	public PlayerManager() {
		players = new CopyOnWriteArrayList<>();
		
	}
	
	public void putPlayer(Player p) {
		if(!p.isActive())
			return;
		players.add(p);
		playerUUIDMap.put(p.getUUID(), p);
	}
	
	public Player getPlayer(UUID uuid) {
		return playerUUIDMap.get(uuid);
	}
	
	/**
	 * Gets a player by there name.
	 * This is not recommended because is performance demanding as it iterates through all players
	 * @param name
	 */	
	@Deprecated
	public Player getPlayer(String name) {
		for(Player p : players) {
			if(p == null)
				continue;
			if(!p.isActive()) {
				synchronize(p);
				return null;
			}
			if(p.getUsername().equals(name))
				return p;
		}
		return null;
	}
	
	public void synchronize(final Player p) {
		if(p == null)
			return;
		if(p.isActive())
			return;
		playerUUIDMap.remove(p.getUUID());
		players.remove(p);
	}
	
	public void forceSynchronization() {
		Player[] snapshot = (Player[]) playerUUIDMap.values().toArray();
		snapshot = Arrays.copyOf(snapshot, snapshot.length);
		for(final Player p : snapshot)
			if(!players.contains(p))
				this.playerUUIDMap.remove(p.getUUID());
		
		snapshot = null;
	}
	
	public void storePlayer(Player p) {
		
	}
}
