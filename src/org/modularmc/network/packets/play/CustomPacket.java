package org.modularmc.network.packets.play;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class CustomPacket extends Packet {
	String channel;
	byte[] data;
	
	public void write(PacketData data) {
		data.writeUTF8VarInt(channel);
		data.writeBytes(this.data);
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0x3f;
	}
}
