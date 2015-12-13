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
	
	final Server server;
	
	boolean isDead;
	
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
		this.server = server;
		this.ch = channel;
		this.packetQueue = new ArrayDeque<>(10);
		System.out.println("Client created");
		state = State.HANDSHAKE;
		
		isDead = false;
	}

	public void process() {
		synchronized (packetQueue) {
			Packet p;

			while ((p = packetQueue.poll()) != null)
				server.getNetwork().getPacketHandler().handle(this, p);
		}
	}
	
	/**
	 * @param p
	 */
	public void accept(Packet p) {
		if(state.equals(State.HANDSHAKE) && p.getID() == 0)
			server.getNetwork().getPacketHandler().handle(this, p);
		else
		synchronized (packetQueue) {
			packetQueue.add(p);
		}
	}

	public void disconnect() {
		disconnect("Your connection was terminated!");
	}
	
	public void disconnect(String message) {
		
		if(state.equals(State.LOGIN))
			sendPacket(new DisconnectPacket(new ChatMessage(message)));
		destroy();
	}
	
	/**
	 * 
	 */
	public void destroy() {
		isDead = true;
		server.getNetwork().removeClient(this);
		ch.close();
		System.out.println("Client died");
	}

	public State getState() {
		return state;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void setState(State nextState) {
		this.state = nextState;
	}
	
	public void sendPacket(Packet p) {
		if(!isDead)
			ch.writeAndFlush(p);
	}
	
}
