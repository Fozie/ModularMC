package org.modularmc.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.modularmc.io.ByteBufUtils;

/**
 * @author Caspar Norée Palm
 */
public class LengthDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> output) throws Exception {
		if(buf.readableBytes() == 0)
			return;
		
		output.add(buf.readBytes(ByteBufUtils.readVarInt(buf)));
	}

}
