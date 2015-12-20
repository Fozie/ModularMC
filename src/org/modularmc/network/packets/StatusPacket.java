package org.modularmc.network.packets;

import org.json.simple.JSONObject;
import org.modularmc.io.ByteUtils;
import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class StatusPacket extends Packet {
	
	@SuppressWarnings("unchecked")
	public void write(PacketData data) {
		final JSONObject status = new JSONObject();
		
		final JSONObject version = new JSONObject();
		version.put("name", "BrickedServer");
		version.put("protocol", 47);
		status.put("version", version);

		final JSONObject players = new JSONObject();
		players.put("max", 0);
		players.put("online", 0);
		status.put("players", players);
		
		final JSONObject description = new JSONObject();
		description.put("text", "TODO: Fix this so its not static :)");
		status.put("description", description);
		final String str = status.toJSONString();
		data.ensureSpace(str.length() + ByteUtils.getVarIntSize(str.length()));
		data.writeUTF8VarInt(str);
	}

	@Override
	public int getID() {
		return 0;
	}
}
