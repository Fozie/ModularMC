package org.modularmc.network.packets.play.player;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class ServerboundPlayerPositionAndLookPacket extends Packet {
	double x,y,z;
	float pitch,yaw;
	boolean onGround;
	
	public void read(PacketData data) {
		x = data.readDouble();
		y = data.readDouble();
		z = data.readDouble();
		yaw = data.readFloat();
		pitch = data.readFloat();
		onGround = data.readBoolean();
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public boolean isOnGround() {
		return onGround;
	}

	@Override
	public int getID() {
		return 0x06;
	}
	
	public ClientboundPlayerPositionAndLookPacket pongPacket() {
		ClientboundPlayerPositionAndLookPacket p = new ClientboundPlayerPositionAndLookPacket();
		p.setPitch(pitch);
		p.setX(x);
		p.setY(y);
		p.setZ(z);
		p.setYaw(yaw);
		return p;
	}
}
