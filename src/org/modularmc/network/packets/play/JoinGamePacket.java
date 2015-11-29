package org.modularmc.network.packets.play;

import org.modularmc.game.world.Difficulty;
import org.modularmc.game.world.Dimension;
import org.modularmc.game.world.Gamemode;
import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class JoinGamePacket extends Packet {
	
	int entityID;
	Gamemode gamemode;
	Dimension dimension;
	Difficulty difficulty;
	
	@Override
	public int getID() {
		return 0x01;
	}
	
	public void write(PacketData out) {
		
	}

}
