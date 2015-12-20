package org.modularmc.network.packets.play.player;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class PlayerLookPacket extends Packet {
	float pitch,yaw;
	boolean onGround;
	
	@Override
	public int getID() {
		return 0x05;
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

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public void read(PacketData data) {
		yaw = data.readFloat();
		pitch = data.readFloat();
		onGround = data.readBoolean();
	}

}
