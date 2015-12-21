package org.modularmc.network.packets.play.world;

import org.modularmc.game.world.Chunk;
import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class ChunkPacket extends Packet {

	Chunk c;
	
	@Override
	public int getID() {
		return 0x21;
	}
	
	public void write(PacketData data) {
		data.writeInt(c.getX());
		data.writeInt(c.getZ());
		
		data.writeBoolean(true);
		
		data.writeShort(c.getSectionBitMap());
		
		byte[] chunkData = c.getData(true, true);
		
		data.writeVarInt(chunkData.length);
		
		data.writeBytes(chunkData);
		
	}

	public Chunk getChunk() {
		return c;
	}

	public void setChunk(Chunk c) {
		this.c = c;
	}
	
}
