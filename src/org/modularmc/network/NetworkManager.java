package org.modularmc.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.modularmc.network.netty.NettyChannelInitializer;
import org.modularmc.server.Server;

/**
 * @author Caspar Norée Palm
 */
public final class NetworkManager {
	
	final Server server;
	
	final EventLoopGroup bossGroup;
	final EventLoopGroup workerGroup;
	
	final List<Client> clients;
	
	PacketHandler packetHandler;
	
	public NetworkManager(Server server, int port) {
		this.server = server;
		
		this.packetHandler = new PacketHandler(server);
		
		clients = new CopyOnWriteArrayList<>();
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap()
											.group(bossGroup, workerGroup)
											.channel(NioServerSocketChannel.class)
											.childHandler(new NettyChannelInitializer(server));
		try {
			bootstrap.bind(new InetSocketAddress("0.0.0.0", port)); //TODO: Configurable listing port and adress
		} catch (Throwable t) {
			System.err.println("Failed to bind to port.");
			System.exit(1);
		}
	}
	
	public void processClients() {
		for (Client c : clients)
			c.process();
	}
	
	
	public void removeClient(final Client c) {
		clients.remove(c);
	}
	
	public void shutdown() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}

	public void addClient(final Client client) {
		if(!clients.contains(client))
			clients.add(client);
	}

	/**
	 * @return
	 */
	public PacketHandler getPacketHandler() {
		return packetHandler;
	}
	
}
