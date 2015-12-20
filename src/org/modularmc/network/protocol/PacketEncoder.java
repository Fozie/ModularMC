package org.modularmc.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.modularmc.io.ByteUtils;
import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {


	@Override
	protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf buf) throws Exception {
		ByteUtils.writeVarInt(buf, packet.getID());
		packet.write(new PacketData(buf));	
	}
	
}
