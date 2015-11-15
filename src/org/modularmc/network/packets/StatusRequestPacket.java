package org.modularmc.network.packets;

import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class StatusRequestPacket extends Packet {
	
	@Override
	public int getID() {
		return 0;
	}
	
	//This packet is just a marker does not contain anything actually :p
}
