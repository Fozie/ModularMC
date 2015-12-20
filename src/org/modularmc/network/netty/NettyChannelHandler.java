package org.modularmc.network.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.modularmc.Server;
import org.modularmc.network.Client;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class NettyChannelHandler extends SimpleChannelInboundHandler<Packet> {

	private final Server server;
	Client client;
	
	public NettyChannelHandler(Server server) {
		super();
		this.server = server;
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if(client == null)
			client = new Client(server, ctx.channel());
	
			server.getNetwork().addClient(client);
	}
	
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		client.destroy();
	}
	
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Packet p) throws Exception {
		client.accept(p);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();

		ctx.close();
	}
	
	public Client getClient() {
		return client;
	}

}
