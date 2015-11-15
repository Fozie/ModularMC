package org.modularmc.network.packets.login;

import org.modularmc.chat.ChatMessage;
import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class DisconnectPacket extends Packet {

	private ChatMessage reason;
	
	public DisconnectPacket(final ChatMessage reason) {
		this.reason = reason;
	}
	
	@Override
	public int getID() {
		return 0;
	}
	
	public void write(PacketData data) {
		data.writeUTF8VarInt(reason.getJSON());
	}

	
	public void setReason(final ChatMessage reason) {
		this.reason = reason;
	}
}	
