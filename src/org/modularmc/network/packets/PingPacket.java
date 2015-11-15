package org.modularmc.network.packets;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class PingPacket extends Packet {

	long time;
	
	@Override
	public int getID() {
		return 1;
	}

	public void read(PacketData data) {
		time = data.readLong();
	}
	
	public void write(PacketData data) {
		data.writeLong(time);
	}
	
	
}
