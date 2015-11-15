package org.modularmc.network;

import org.modularmc.io.PacketData;

/**
 * @author Caspar Norée Palm
 */
public abstract class Packet {
	public void read(PacketData input) {}
	public void write(PacketData out) {}
	
	public abstract int getID();
	
}
