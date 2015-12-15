package org.modularmc.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.modularmc.Logger;
import org.modularmc.io.ByteBufUtils;
import org.modularmc.io.PacketData;
import org.modularmc.network.Client;
import org.modularmc.network.Packet;
import org.modularmc.network.netty.NettyChannelHandler;

/**
 * @author Caspar Norée Palm
 */
public class PacketDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf,	List<Object> output) throws Exception {
		
		if(buf.readableBytes() == 0)
			return;
		
		int id =  ByteBufUtils.readVarInt(buf);
		
		Client.State state = ctx.pipeline().get(NettyChannelHandler.class).getClient().getState();
		
		Protocol protocol = Protocol.PROTOCOL;
		
		if(protocol.hasPacket(state, id)) {
			Packet p = protocol.createPacket(state, id);
			try {
				p.read(new PacketData(buf));
			}
			catch(Exception e) {
				ctx.channel().close();
				return;
			}
			buf.skipBytes(buf.readableBytes());
			
			output.add(p);
		}
		else {
			Logger.warning("Unknown packet was intercepted (ID: "+ state.name() +":"+ id + ")");
			buf.skipBytes(buf.readableBytes());
		}
	}
	
}