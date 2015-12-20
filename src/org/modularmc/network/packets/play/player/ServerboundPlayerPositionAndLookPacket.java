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
	
	@Override
	public int getID() {
		return 0x06;
	}
}
