package org.modularmc.network.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import org.modularmc.network.protocol.LengthDecoder;
import org.modularmc.network.protocol.LengthEncoder;
import org.modularmc.network.protocol.PacketDecoder;
import org.modularmc.network.protocol.PacketEncoder;
import org.modularmc.server.Server;

/**
 * @author Caspar Norée Palm
 */
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	final Server server;
	
	public NettyChannelInitializer(final Server server) {
		super();
		this.server = server;
	}
	
	@Override
	protected void initChannel(SocketChannel sc) throws Exception {
		sc.pipeline()
		.addLast("lengthdecoder", new LengthDecoder())
		.addLast("decoder", new PacketDecoder())
		
		.addLast("lengthencoder", new LengthEncoder())
		.addLast("encoder", new PacketEncoder())
		
		.addLast("handler", new NettyChannelHandler(server));
	}

}
