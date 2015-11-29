package org.modularmc.network.packets.login;

import org.modularmc.chat.ChatMessage;
import org.modularmc.io.ByteBufUtils;
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
		String reason = this.reason.getJSON();
		data.ensureSpace(reason.length() + ByteBufUtils.getVarIntSize(reason.length()));
		data.writeUTF8VarInt(reason);
	}

	
	public void setReason(final ChatMessage reason) {
		this.reason = reason;
	}
}	
