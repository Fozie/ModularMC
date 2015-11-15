package org.modularmc.network.packets.login;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class LoginPacket extends Packet {
	String username;

	@Override
	public int getID() {
		return 0;
	}
	
	public void read(PacketData data) {
		username = data.readUTF8VarInt();
	}
	
}
