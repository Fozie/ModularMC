package org.modularmc.network;

/**
 * @author Caspar Norée Palm
 */
public class ClientThread extends Thread {
	
	final NetworkManager net;
	
	public ClientThread(final NetworkManager net) {
		super("ClientThread");
		this.net = net;
	}
	
	public void run() {
		for(;;) {
			net.pushAllClients();
			try {
				ClientThread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}

}
