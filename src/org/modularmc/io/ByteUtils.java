package org.modularmc.io;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;

/**
 * @author Caspar Norée Palm
 */
public class ByteUtils {
	
	public static byte[] writeUTF8VarInt(String str) {
		byte[] b = new byte[getVarIntSize(str.length()) + str.length()]; 
		int i = 0;
		byte part;
		int v = str.length();
		while (true) {
			part = (byte) (v & 0x7F);
			v >>>= 7;
			if (v != 0)
				part |= 0x80;
			b[i++] = part;
			if (v == 0)
				break;
		}
		
		System.arraycopy(str.getBytes(StandardCharsets.UTF_8), 0, b, i, str.length());
		return b;
	}
	
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
	
	public static int getVarIntSize(int varint) {
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
