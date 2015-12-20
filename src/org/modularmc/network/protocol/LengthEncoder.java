package org.modularmc.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.modularmc.io.ByteUtils;

/**
 * @author Caspar Norée Palm
 */
public class LengthEncoder extends MessageToByteEncoder<ByteBuf> {

	@Override
	protected void encode(ChannelHandlerContext ctx, ByteBuf buf, ByteBuf output) throws Exception {
		int dataLength = buf.readableBytes();
		int headerLength = headerSize(dataLength);
		output.ensureWritable(dataLength + headerLength);
		
		ByteUtils.writeVarInt(output, dataLength);
		output.writeBytes(buf);
		
	}
	
	private static int headerSize(int varint) {
		if ((varint & 0xFFFFFF80) == 0)
			return 1;

		if ((varint & 0xFFFFC000) == 0)
			return 2;

		if ((varint & 0xFFE00000) == 0)
			return 3;
		
		if ((varint & 0xF0000000) == 0)
			return 4;

		return 5;
	}
	
}
