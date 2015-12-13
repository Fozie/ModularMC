package org.modularmc.network.packets.login;

import java.util.UUID;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class LoginSucessPacket extends Packet {

	String username;
	UUID uuid;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public int getID() {
		return 0x02;
	}
	
	public void write(PacketData out) {
		out.writeUUID(uuid);
		out.writeUTF8VarInt(username);
	}
	
}
