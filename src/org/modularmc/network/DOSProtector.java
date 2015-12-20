package org.modularmc.network;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Caspar Norée Palm
 */
public class DOSProtector { // W.I.P
	public static final DOSProtector instance = new DOSProtector();
	
	private ConcurrentHashMap<Integer, Integer> connectionMap;
	
	public int MAX_CONNECTIONS_PER_MINUTE;
	
	private DOSProtector() {
		connectionMap = new ConcurrentHashMap<>();
		MAX_CONNECTIONS_PER_MINUTE = 30;
		new ResetThread().start();
	}
	
	public boolean registerConnection(Integer id) {
		Integer i = connectionMap.get(id);
		connectionMap.replace(id, (i == null) ? 1 : i+1);
		return (i == null) ? false : i.intValue() > MAX_CONNECTIONS_PER_MINUTE;
	}
	
	private void reset() {
		
		connectionMap.clear();
	}
	
	private class ResetThread extends Thread {
		public ResetThread() {
			super("DOS-PROTECTION Service");
		}
		
		public void run() {
			reset();
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {}
		}
	}

}
