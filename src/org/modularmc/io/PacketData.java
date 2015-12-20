package org.modularmc.io;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.modularmc.utils.Vec3i;

/**
 * @author Caspar Nor�e Palm
 */
public class PacketData {
	private final ByteBuf data;
	
	public PacketData(final ByteBuf buf) {
		this.data = buf;
	}
	
	public boolean readBoolean() {
		return data.readByte() == 0x01;
	}

	public short readShort() {
		return data.readShort();
	}

	public int readUnsignedShort() {
		return data.readUnsignedShort();
	}

	public int readInt() {
		return data.readInt();
	}

	public long readLong() {
		return data.readLong();
	}
	
	public float readFloat() {
		return Float.intBitsToFloat(readInt());
	}

	public double readDouble() {
		return Double.longBitsToDouble(readLong());
	}

	public int readVarInt() {
		int out = 0;
		int bytes = 0;
		byte in;
		while (true) {
			in = data.readByte();;
			out |= (in & 0x7F) << bytes++ * 7;
			if ((in & 0x80) != 0x80)
				break;
		}
		return out;
	}

	public long readVarLong() {
		long out = 0;
		int bytes = 0;
		byte in;
		while (true) {
			in = data.readByte();
			out |= (in & 0x7F) << bytes++ * 7;
			if ((in & 0x80) != 0x80)
				break;
		}
		return out;
	}

	public byte[] read(final byte... input) {
		data.readBytes(input);
		return input;
	}

	public byte[] readBytes(final int size) {
		final byte[] r = new byte[size];
		data.readBytes(r);
		return r;
	}

	public String readString(final int size, final Charset charset) {
		return new String(readBytes(size), charset);
	}
	
	public String readUTF8VarInt() {
		return readString(readVarInt(), StandardCharsets.UTF_8);
	}
	
	public String readUTF16VarInt() {
		return readString(readVarInt() * 2, StandardCharsets.UTF_16);
	}
	
	public void writeByte(final byte v) {
		data.writeByte(v);
	}
	
	public void writeBoolean(final boolean v) {
		if (v)
			data.writeByte((byte) 0x01);
		else
			data.writeByte((byte) 0x00);
	}

	
	public void writeShort(final short v) {
		data.writeShort(v);
	}

	
	public void writeInt(final int v) {
		data.writeInt(v);
	}

	
	public void writeLong(final long v) {
		data.writeLong(v);
	}

	
	public void writeFloat(final float v) {
		writeInt(Float.floatToIntBits(v));
	}

	
	public void writeDouble(final double v) {
		writeLong(Double.doubleToLongBits(v));
	}
	
	public void writePosition(int x, int y, int z) {
		writeLong(((x & 0x3FFFFFF) << 38) | ((y & 0xFFF) << 26) | (z & 0x3FFFFFF));
	}
	
	public Vec3i readPosition() {
		long val = readLong();
		Vec3i vec = new Vec3i();
		vec.setX((int) (val >> 38));
		vec.setY((int) ((val >> 26) & 0xFFF));
		vec.setZ((int) (val << 38 >> 38));
		return vec;
	}
	public void writeVarInt(int v) {
		byte part;
		while (true) {
			part = (byte) (v & 0x7F);
			v >>>= 7;
			if (v != 0)
				part |= 0x80;
			data.writeByte(part);
			if (v == 0)
				break;
		}
	}

	
	public void writeVarLong(long v) {
		byte part;
		while (true) {
			part = (byte) (v & 0x7F);
			v >>>= 7;
			if (v != 0)
				part |= 0x80;
			data.writeByte(part);
			if (v == 0)
				break;
		}
	}

	public void writeUUID(UUID uuid) {
		this.writeUTF8VarInt(uuid.toString());
	}
	
	public void writeString(final String s, final Charset charset) {
		data.writeBytes(s.getBytes(charset));
	}
	
	public void writeUTF8VarInt(final String s) {
		writeVarInt(s.length());
		data.writeBytes(s.getBytes(StandardCharsets.UTF_8));
	}
	
	public void writeUTF16VarInt(final String s) {
		writeVarInt(s.length() * 2);
		data.writeBytes(s.getBytes(StandardCharsets.UTF_16));
	}
	
	public ByteBuf getUnderlyingBuffer() {
		return data;
	}
	
	public void ensureSpace(int numBytes) {
		data.ensureWritable(numBytes);
	}

	public void writeBytes(byte[] bytes) {
		data.writeBytes(bytes);
	}
}
