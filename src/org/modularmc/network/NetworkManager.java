package org.modularmc.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
	final Queue<Client> pendingClients;
	
	
	public NetworkManager(Server server, int port) {
		this.server = server;
		
		clients = new ArrayList<Client>();
		pendingClients = new ArrayDeque<Client>();
		
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap()
											.group(bossGroup, workerGroup)
											.channel(NioServerSocketChannel.class)
											.childHandler(new NettyChannelInitializer(server));
		try {
			bootstrap.bind(new InetSocketAddress("0.0.0.0", port)); //TODO: Configurable listing port
		} catch (Throwable t) {
			System.err.println("Failed to bind to port.");
			System.exit(1);
		}
		
		new ClientThread(this).start();
	}
	
	public void handleAllPackets() {
		synchronized(clients) {
			for(final Client c : clients)
				c.process();
		}
	}
	
	public void pushAllClients() {
			synchronized(clients) {
				Client c;
				synchronized(pendingClients) {
					while((c = pendingClients.poll()) != null)
						clients.add(c);
				}
			}
	}
	
	public void shutdown() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}

	public void addClient(final Client client) {
		synchronized(pendingClients) {
			pendingClients.add(client);
		}
	}
	
}
