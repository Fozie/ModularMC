package org.modularmc;

import org.modularmc.game.PlayerManager;
import org.modularmc.game.WorldManager;
import org.modularmc.network.NetworkManager;

/**
* @author Caspar Norée Palm
 */
public class Server {
	private final NetworkManager net;
	
	public PlayerManager manager;
	
	public static final int GAME_TICK_RATE = 20;
	
	public WorldManager worldManager;
	
	private int tps; //Ticks per second should be equal to GAME_TICK_RATE
	
	public Server() throws Exception {
		net = new NetworkManager(this, 25565); //TODO: Config
		
		worldManager = new WorldManager(this);
		
		long currentTime = System.currentTimeMillis();
		long lastTickTime = 0;
		long waitPerTick = 1000 / GAME_TICK_RATE;
		long lastSecondTime = currentTime;
		
		
		while(true) {
			currentTime = System.currentTimeMillis();
			if(currentTime-lastSecondTime >= 1000) {
				lastSecondTime = currentTime;
				secondTick();
			}
			
			if(currentTime-lastTickTime >= waitPerTick) {
				lastTickTime = currentTime;
				tick();
			}
			
			process();
			Thread.sleep(1);
		}
	}
	
	public void tick() {
		++tps;
	}
	
	public void secondTick() {
		if(tps < 19)
			Logger.warning("Warning skipped " + (20-tps) + "ticks! Server may be overloaded.");
		tps = 0;
	}
	
	public void process() {
		net.processClients();
		net.getPacketHandler().getLoginHandler().process();
	}
//	net.processClients();
//	
//	net.getPacketHandler().getLoginHandler().process();
//	
//	Thread.sleep(10);
	
	public NetworkManager getNetwork() {
		return net;
	}
}
