package org.modularmc.network;

import java.lang.reflect.InvocationTargetException;

import org.modularmc.Logger;
import org.modularmc.Server;
import org.modularmc.handlers.LoginHandler;
import org.modularmc.network.packets.HandshakePacket;
import org.modularmc.network.packets.PingPacket;
import org.modularmc.network.packets.StatusPacket;
import org.modularmc.network.packets.StatusRequestPacket;
import org.modularmc.network.packets.login.LoginPacket;
import org.modularmc.network.packets.play.player.ClientboundPlayerPositionAndLookPacket;
import org.modularmc.network.packets.play.player.PlayerLookPacket;
import org.modularmc.network.packets.play.player.PlayerPacket;
import org.modularmc.network.packets.play.player.PlayerPositionPacket;
import org.modularmc.network.packets.play.player.ServerboundPlayerPositionAndLookPacket;

/**
 * @author Caspar Norée Palm
 */
public class PacketHandler {
	
	final Server server;
	
	final LoginHandler loginHandler;
	
	public PacketHandler(Server server) {
		this.server = server;
		loginHandler = new LoginHandler(server);
	}

	public <T extends Packet> void handle(Client c, T packet) {
		try {
			PacketHandler.class.getMethod("handle", Client.class, packet.getClass()).invoke(this, c, packet);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			Logger.info("Cant handle packet: " + packet.getClass().getSimpleName());
		}
	}
	
	public void handle(Client c, PlayerPositionPacket p) {
//		Player pl = c.getPlayer();
//		if(pl == null)
//			throw new RuntimeException("A player packet got handled without a player object.");
//		pl.getLocation().updateFromPacket(p);
//		
	}
	
	public void handle(Client c, PlayerLookPacket p) {
//		Player pl = c.getPlayer();
//		if(pl == null)
//			throw new RuntimeException("A player packet got handled without a player object.");
//		pl.getLocation().updateFromPacket(p);
	}
	

	public void handle(Client c, PlayerPacket p) {
	}
	
	public void handle(Client c, ServerboundPlayerPositionAndLookPacket p) {
	//c.sendPacket(p.pongPacket());
//		Player pl = c.getPlayer();
//		if(pl == null)
//			throw new RuntimeException("A player packet got handled without a player object.");
//		pl.getLocation().updateFromPacket(p);
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
		//c.disconnect("WIP");
		loginHandler.addClient(c);
	}

	/**
	 * @return
	 */
	public LoginHandler getLoginHandler() {
		return loginHandler;
	}
}
