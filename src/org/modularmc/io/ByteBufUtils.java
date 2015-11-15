package org.modularmc.io;

import io.netty.buffer.ByteBuf;

/**
 * @author Caspar Norée Palm
 */
public class ByteBufUtils {
	public static int readVarInt(ByteBuf byteBuf) {
		int out = 0;
		int numBytes = 0;
		byte in;
		while (true) {
			in = byteBuf.readByte();
			out |= (in & 0x7F) << numBytes++ * 7;
			if ((in & 0x80) != 0x80)
				break;
		}
		return out;
	}
	
	public static void writeVarInt(ByteBuf byteBuf, int v) {
		byte part;
		while (true) {
			part = (byte) (v & 0x7F);
			v >>>= 7;
			if (v != 0)
				part |= 0x80;
			byteBuf.writeByte(part);
			if (v == 0)
				break;
		}
	}
}
