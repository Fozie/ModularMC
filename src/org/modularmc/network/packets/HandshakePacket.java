package org.modularmc.network.packets;

import org.modularmc.io.PacketData;
import org.modularmc.network.Client;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class HandshakePacket extends Packet {

	int protocolVersion;
	String serverAddress;
	int port;
	int nextState;
	
	public void read(PacketData input) {
		protocolVersion = input.readVarInt();
		serverAddress = input.readUTF8VarInt();
		port = input.readUnsignedShort();
		nextState = input.readVarInt();
		
		System.out.println("Handshake: " + protocolVersion + " " + serverAddress + ":" + port + " State:" + nextState);
	}
	
	public Client.State getNextState() {
		switch(nextState) {
			case 1: return Client.State.STATUS;
			case 2: return Client.State.LOGIN;
		}
		new Exception("Wtf, something happend here...").printStackTrace();
		return Client.State.HANDSHAKE;
	}

	@Override
	public int getID() {
		return 0;
	}
}
