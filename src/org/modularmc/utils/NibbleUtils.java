package org.modularmc.utils;

/**
 * @author Caspar Norée Palm
 */
public class NibbleUtils {
    public static byte get(byte[] data, int pos) {
        byte r = data[pos / 2];
        if (pos % 2 == 0) {
            return (byte) (r & 0x0f);
        } else {
            return (byte) ((r & 0xf0) >> 4);
        }
    }
    
    public static void set(byte[] data, int pos, byte value) {
        value &= 0xf;
        int index = pos / 2;
        byte half = data[index];
        if (pos % 2 == 0) {
            data[index] = (byte) ((half & 0xf0) | value);
        } else {
            data[index] = (byte) ((half & 0x0f) | (value << 4));
        }
    }
}
