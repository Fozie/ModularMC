package org.modularmc.network;

import java.lang.reflect.InvocationTargetException;

import org.modularmc.Logger;
import org.modularmc.network.packets.HandshakePacket;
import org.modularmc.network.packets.PingPacket;
import org.modularmc.network.packets.StatusPacket;
import org.modularmc.network.packets.StatusRequestPacket;
import org.modularmc.network.packets.login.LoginPacket;

/**
 * @author Caspar Norée Palm
 */
public class PacketHandler {
	
	static PacketHandler handler = new PacketHandler();
	
	public static <T extends Packet> void handle(Client c, T packet) {
		try {
			PacketHandler.class.getMethod("handle", Client.class, packet.getClass()).invoke(handler, c, packet);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			Logger.info("Cant handle packet: " + packet.getClass().getSimpleName());
		}
	}
	
	public void handle(Client c, HandshakePacket packet) {
		c.setState(packet.getNextState());
		System.out.println("SET:" + packet.getNextState().name());
	}
	
	public void handle(Client c, StatusRequestPacket packet) {
		c.sendPacket(new StatusPacket());
	}
	
	public void handle(Client c, PingPacket packet) {
		c.sendPacket(packet);
	}
	
	public void handle(Client c, LoginPacket packet) {
		c.kick("Sorry, WIP");
	}
}
