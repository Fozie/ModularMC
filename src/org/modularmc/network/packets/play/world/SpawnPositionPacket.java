package org.modularmc.network.packets.play.world;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class SpawnPositionPacket extends Packet {
	private int x,y,z;
	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void write(PacketData data) {
		data.writePosition(x, y, z);
	}

	@Override
	public int getID() {
		return 0x05;
	}
}
