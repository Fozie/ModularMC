package org.modularmc.game;

import org.modularmc.game.entities.Human;
import org.modularmc.game.world.World;
import org.modularmc.network.Client;
import org.modularmc.network.packets.play.player.ClientbondPlayerPositionAndLookPacket;

/**
 * @author Caspar Norée Palm
 */
public class Player extends Human {
	
	final Client c;
	
	public Player(Client c, World w) {
		super(w);
		this.c = c;
	}
	
	public void sendPosition() {
		ClientbondPlayerPositionAndLookPacket p = new ClientbondPlayerPositionAndLookPacket();
		p.setX(getX());
		p.setY(getY());
		p.setZ(getZ());
		
		p.setYaw(getYaw());
		p.setPitch(getPitch());
		
		c.sendPacket(p);
	}
}
