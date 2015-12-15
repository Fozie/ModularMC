package org.modularmc.network.packets.play.player;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class ClientbondPlayerPositionAndLookPacket extends Packet {
	double x,y,z;
	float pitch,yaw;
	
	@Override
	public int getID() {
		return 0x08;
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

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void write(PacketData out) {
		out.writeDouble(x);
		out.writeDouble(y);
		out.writeDouble(z);
		
		out.writeFloat(yaw);
		out.writeFloat(pitch);
		
		out.writeByte(Byte.parseByte("01111111", 2)); // TODO: MAKE THIS CHANGABLE!
	}
	
}
