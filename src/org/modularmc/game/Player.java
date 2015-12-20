package org.modularmc.game;

import java.util.ArrayList;
import java.util.UUID;

import org.modularmc.game.entities.Human;
import org.modularmc.game.world.Chunk;
import org.modularmc.game.world.World;
import org.modularmc.network.Client;
import org.modularmc.network.Packet;
import org.modularmc.network.packets.play.player.ClientboundPlayerPositionAndLookPacket;
import org.modularmc.network.packets.play.world.ChunkBulkPacket;

/**
 * @author Caspar Norée Palm
 */
public class Player extends Human {
	
	final Client c;
	
	/**
	 * The player identification, uuid and username
	 * Both are finals and cannot be changed during 
	 */
	final PlayerID uid;
	
	int viewDistance;
	
	public ArrayList<Long> knownChunks;
	
	private boolean isActive;
	
	public Player(Client c, PlayerID uid, World w) {
		super(w);
		this.c = c;
		this.uid = uid;
	}
	
	public void sendChunks(Chunk... chunks) {
		ChunkBulkPacket p = new ChunkBulkPacket();
		p.setSendSkyLight(true);
	}
	
	/**
	 * Used to update(synchronize) the player
	 * VERY DIFFRENT from the tick method!!!
	 */
	public void update() {
		updateNearbyChunks(viewDistance);
		
	}
	
	/**
	 * Will send or update any chunks within the specified viewing distance.
	 * @param viewDistance
	 */
	public void updateNearbyChunks(int viewDistance) {
		
	}
	
	public void sendPosition() {
		ClientboundPlayerPositionAndLookPacket p = new ClientboundPlayerPositionAndLookPacket();
		p.setX(getX());
		p.setY(getY());
		p.setZ(getZ());
		
		p.setYaw(getYaw());
		p.setPitch(getPitch());
		
		c.sendPacket(p);
	}
	
	public UUID getUUID() {
		return uid.uuid;
	}
	
	public PlayerID id() {
		return uid;
	}
	
	public boolean isActive() {
		return c.isActive();
	}

	public String getUsername() {
		return uid.username;
	}
	
	public boolean equals(Player p) {
		return p.id().equals(p.uid);
				
	}
	
	/**
	 * Finalizes this object ensure its removed from all lists and references
	 */
	public void close() {} //TODO
	
	public void sendPacket(Packet p) {
		if(isActive())
			c.sendPacket(p);
		else
			close();
	}
	
	
	public static class PlayerID {
		final UUID uuid;
		final String username;
		
		public PlayerID(UUID uid, String name) {
			this.uuid = uid;
			this.username = name;
			uuid.hashCode(); // Precalculates the uuid's hashcode
		}

		public UUID getUUID() {
			return uuid;
		}

		public String getUsername() {
			return username;
		}

		public int hashCode() {
			return uuid.hashCode();
		}
		
		public boolean equals(Object other) {
			if(!(other instanceof PlayerID))
				return false;
			PlayerID p = (PlayerID) other;
			return (p.getUUID().getMostSignificantBits() == getUUID().getMostSignificantBits() &&
					p.getUUID().getLeastSignificantBits()== getUUID().getLeastSignificantBits());
		}
		
	}
}
