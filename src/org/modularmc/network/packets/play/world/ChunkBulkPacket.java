package org.modularmc.network.packets.play.world;

import org.modularmc.game.world.Chunk;
import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class ChunkBulkPacket extends Packet {
	
	public boolean isSendSkyLight() {
		return sendSkyLight;
	}

	public void setSendSkyLight(boolean sendSkyLight) {
		this.sendSkyLight = sendSkyLight;
	}

	public Chunk[] getChunks() {
		return chunks;
	}

	public void setChunks(Chunk[] chunks) {
		this.chunks = chunks;
	}

	boolean sendSkyLight;
	Chunk[] chunks;
	
	@Override
	public int getID() {
		return 0x26;
	}
	
	public void write(PacketData data) {
		data.writeBoolean(sendSkyLight);
		data.writeVarInt(chunks.length);
		
		for(Chunk c : chunks) {
			data.writeInt(c.getX());
			data.writeInt(c.getZ());
			data.writeShort(c.getSectionBitMap());
		}
		
		for(Chunk c : chunks) {
			data.writeBytes(c.getData(sendSkyLight, true));
		}
		
		
	}
	
}
