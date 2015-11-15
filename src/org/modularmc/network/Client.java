package org.modularmc.network;

import io.netty.channel.Channel;

import java.util.ArrayDeque;
import java.util.Queue;

import org.modularmc.chat.ChatMessage;
import org.modularmc.network.packets.login.DisconnectPacket;
import org.modularmc.server.Server;

/**
 * @author Caspar Norée Palm
 */
public class Client {
	
	private final Queue<Packet> packetQueue;
	
	public enum State {
		HANDSHAKE,
		STATUS,
		LOGIN,
		PLAY,
	}

	
	private State state;
	
	final Channel ch;
	
	public Client(Server server, Channel channel) {
		this.ch = channel;
		this.packetQueue = new ArrayDeque<>(10);
		System.out.println("Client created");
		state = State.HANDSHAKE;
	}

	public void process() {
		synchronized (packetQueue) {
			Packet p;

			while ((p = packetQueue.poll()) != null)
				PacketHandler.handle(this, p);
		}
	}
	
	/**
	 * @param p
	 */
	public void accept(Packet p) {
		synchronized (packetQueue) {
			packetQueue.add(p);
		}
	}

	public void kick() {
		kick("Your connection was terminated! (Kicked)");
	}
	
	public void kick(String reason) {
		
		if(state.equals(State.LOGIN))
			sendPacket(new DisconnectPacket(new ChatMessage(reason)));
		destroy();
	}
	
	/**
	 * 
	 */
	public void destroy() {
		ch.close();
		System.out.println("Client died");
	}

	public State getState() {
		return state;
	}
	
	
	public void setState(State nextState) {
		this.state = nextState;
	}
	
	public void sendPacket(Packet p) {
		ch.writeAndFlush(p);
	}
	
}
