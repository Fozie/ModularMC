package org.modularmc.network.packets.play.player;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class PlayerPacket extends Packet {
	boolean onGround;
	
	public boolean isOnGround() {
		return onGround;
	}
	
	public void read(PacketData data) {
		onGround = data.readBoolean();
	}

	@Override
	public int getID() {
		return 0x03;
	}
	
	
}
