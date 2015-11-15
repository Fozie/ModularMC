package org.modularmc.network.protocol;

import org.modularmc.network.Packet;
import org.modularmc.network.Client.State;
import org.modularmc.network.packets.HandshakePacket;
import org.modularmc.network.packets.PingPacket;
import org.modularmc.network.packets.StatusRequestPacket;
import org.modularmc.network.packets.login.LoginPacket;

/**
 * @author Caspar Norée Palm
 */
public class Protocol {
	
	public static final Protocol PROTOCOL = new Protocol();
	
	public boolean hasPlayPacket(int id) {
		switch(id) {
			default: return false;
		}
	}

	public Packet createPlayPacket(int id) {
		switch(id) {
			default: return null;
		}
	}
	
	public boolean hasLoginPacket(int id) {
		switch(id) {
			case 0: return true;
			default: return false;
		}
	}

	public Packet createLoginPacket(int id) {
		switch(id) {
			case 0: return new LoginPacket();
			default: return null;
		}
	}
	
	public boolean hasHandshakePacket(int id) {
		if(id == 0) 
			return true;
		return false;
	}

	public Packet createHandshakePacket(int id) {
		return new HandshakePacket();
	}
	
	public boolean hasStatusPacket(int id) {
		switch(id) {
			case 0: return true;
			case 1: return true;
			default: return false;
		}
	}

	public Packet createStatusPacket(int id) {
		switch(id) {
			case 0: return new StatusRequestPacket();
			case 1: return new PingPacket();
			default: return null;
		}
	}

	public boolean hasPacket(State state, int id) {
		switch(state) {
			case PLAY: return hasPlayPacket(id);
			case LOGIN: return hasLoginPacket(id);
			case STATUS: return hasStatusPacket(id);
			case HANDSHAKE: return hasHandshakePacket(id);
		}
		return false;
	}

	public Packet createPacket(State state, int id) {
		switch(state) {
		case PLAY: return createPlayPacket(id);
		case LOGIN: return createLoginPacket(id);
		case STATUS: return createStatusPacket(id);
		case HANDSHAKE: return createHandshakePacket(id);
		}
		new Exception("Wtf, something happend here...").printStackTrace();
		return null;
	}
	
	
}
