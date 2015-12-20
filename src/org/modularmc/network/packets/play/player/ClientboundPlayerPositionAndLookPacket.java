package org.modularmc.network.packets.play.player;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class ClientboundPlayerPositionAndLookPacket extends Packet {
	
	double x,y,z;
	float pitch,yaw;
	
	@Override
	public int getID() {
		return 0x08;
	}

	
	public void write(PacketData data) {
		data.writeDouble(x);
		data.writeDouble(y);
		data.writeDouble(z);

		data.writeFloat(yaw);
		data.writeFloat(pitch);

		data.writeByte((byte) 0);
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
}
