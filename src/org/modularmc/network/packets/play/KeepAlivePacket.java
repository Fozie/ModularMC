package org.modularmc.network.packets.play;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class KeepAlivePacket extends Packet {
	int id;

	@Override
	public int getID() {
		return 0;
	}

	public void write(PacketData out) {
		out.writeVarInt(id);
	}
}
	