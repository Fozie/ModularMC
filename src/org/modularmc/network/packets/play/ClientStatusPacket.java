package org.modularmc.network.packets.play;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class ClientStatusPacket extends Packet {

	int nextAction;
//	0 = PreformRespawn
//	1 = Request Stats
//	2 = Taking Inventory Achievement
	
	@Override
	public int getID() {
		return 0x16;
	}
	
	public int getNextAction() {
		return nextAction;
	}

	public void read(PacketData data) {
		nextAction = data.readVarInt();
	}

}
