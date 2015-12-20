package org.modularmc;

import org.modularmc.handlers.LoginHandler;
import org.modularmc.network.NetworkManager;

/**
 * @author Caspar Norée Palm
 */
public class Server {
	private final NetworkManager net;
	
	private LoginHandler loginHandler;
	
	public Server() throws Exception {
		net = new NetworkManager(this, 25565); //TODO: Config
		loginHandler = new LoginHandler(this);
		
		while(true) {
			net.processClients();
			
			net.getPacketHandler().getLoginHandler().process();
			
			Thread.sleep(10);
		}
	}
	
	public NetworkManager getNetwork() {
		return net;
	}
}
