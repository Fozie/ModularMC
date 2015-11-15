package org.modularmc;

import org.modularmc.server.Server;

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