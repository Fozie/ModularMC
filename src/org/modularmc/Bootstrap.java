package org.modularmc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import org.modularmc.io.ByteBufUtils;

/**
 * @author Caspar Norée Palm
 */
public class Bootstrap {
	
	public static void main(String[] args) {
		Logger.info("Hello World");
		
		try {
			new Server();
		} catch (Exception e) {
			Logger.error("SERVER FAILED :(");
			e.printStackTrace();
		}
		
	}
	
}