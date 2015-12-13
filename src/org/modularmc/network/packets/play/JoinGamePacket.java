package org.modularmc.network.packets.play;

import org.modularmc.game.world.Difficulty;
import org.modularmc.game.world.Dimension;
import org.modularmc.game.world.Gamemode;
import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class JoinGamePacket extends Packet {
	
	int entityID;
	Gamemode gamemode;
	Dimension dimension;
	Difficulty difficulty;
	
	@Override
	public int getID() {
		return 0x01;
	}
	
	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}

	public void setGamemode(Gamemode gamemode) {
		this.gamemode = gamemode;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void write(PacketData out) {
		out.writeInt(entityID);
		out.writeByte((byte) gamemode.getID());
		out.writeByte(dimension.getID());
		out.writeByte(difficulty.getID());
		out.writeByte((byte) 1);
		out.writeUTF8VarInt("default"); //TODO: Use the worlds actual leveltype?
		out.writeBoolean(false); //TODO: Change later
	}

}
