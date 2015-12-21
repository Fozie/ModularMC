package org.modularmc.network;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

import org.modularmc.io.PacketData;
import org.modularmc.utils.Vec3i;

/**
 * @author Caspar Norée Palm
 */
public abstract class Packet {
	
	public void read(PacketData input) {}
	public void write(PacketData out) {}
	
	public abstract int getID();
}
