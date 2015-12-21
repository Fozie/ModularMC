package org.modularmc;

/**
 * @author Caspar Nor�e Palm
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