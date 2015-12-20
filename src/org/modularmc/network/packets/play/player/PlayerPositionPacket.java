package org.modularmc.network.packets.play.player;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class PlayerPositionPacket extends Packet {
	double x,y,z;
	boolean onGround;
	
	@Override
	public int getID() {
		return 0x04;
	}
	
	public void read(PacketData data) {
		x = data.readDouble();
		y = data.readDouble();
		z = data.readDouble();
		onGround = data.readBoolean();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	
}
