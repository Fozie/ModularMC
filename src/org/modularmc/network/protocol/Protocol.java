package org.modularmc.network.protocol;

import java.util.HashMap;

import org.modularmc.network.Client.State;
import org.modularmc.network.Packet;
import org.modularmc.network.packets.HandshakePacket;
import org.modularmc.network.packets.PingPacket;
import org.modularmc.network.packets.StatusRequestPacket;
import org.modularmc.network.packets.login.LoginPacket;

/**
 * @author Caspar Norée Palm
 */
public class Protocol {
	
	public static final Protocol PROTOCOL = new Protocol();
	
	static {
		PROTOCOL.statusPacket.put(0, StatusRequestPacket.class);
		PROTOCOL.statusPacket.put(1, PingPacket.class);
		
		PROTOCOL.loginPackets.put(0, LoginPacket.class);
	}
	
	
	private final HashMap<Integer, Class<? extends Packet>> statusPacket = new HashMap<>(); //Handshake packets, status packets.
	private final HashMap<Integer, Class<? extends Packet>> loginPackets     = new HashMap<>();
	private final HashMap<Integer, Class<? extends Packet>> playPackets = new HashMap<>();

	public boolean hasPacket(State state, int id) {
		switch(state) {
			case PLAY: 		return playPackets.containsKey(id);
			case LOGIN: 	return loginPackets.containsKey(id);
			case STATUS: 	return statusPacket.containsKey(id);
			case HANDSHAKE: return (id == 0) ? true : false;
		}
		return false;
	}

	public Packet createPacket(State state, int id) {
		try {
			switch(state) {
				case PLAY: 		return playPackets.get(id).newInstance();
				case LOGIN: 	return loginPackets.get(id).newInstance();
				case STATUS: 	return statusPacket.get(id).newInstance();
				case HANDSHAKE: return (id == 0) ? new HandshakePacket() : null;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			new Exception("Packet(ID:"+ state.name().toLowerCase() + "," + id + ") had an invalid constructor." + " E: " + e.getMessage()).printStackTrace();
		}
		return null;
	}
}
