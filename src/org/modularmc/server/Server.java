package org.modularmc.server;

import org.modularmc.network.NetworkManager;

/**
 * @author Caspar Norée Palm
 */
public class Server {
	private final NetworkManager net;
	
	public Server() throws Exception {
		net = new NetworkManager(this, 25565); //TODO: Config
		
		
		while(true) {
			Thread.sleep(10);
		}
	}
	
	public NetworkManager getNetwork() {
		return net;
	}
}
