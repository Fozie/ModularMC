package org.modularmc.game.entities;

import org.modularmc.network.packets.play.player.PlayerLookPacket;
import org.modularmc.network.packets.play.player.PlayerPositionPacket;
import org.modularmc.network.packets.play.player.ServerboundPlayerPositionAndLookPacket;

/**
 * @author Caspar Norée Palm
 */
public class EntityLocation {
	private double x,y,z;
	private float pitch,yaw;
	
	public EntityLocation() {
		this(0,0,0,0,0);
	}
	
	public EntityLocation(double x, double y, double z, float pitch, float yaw) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public float getPitch() {
		return pitch;
	}
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	public float getYaw() {
		return yaw;
	}
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	/**
	 * Only to be invoked when entity is a player
	 */
	public void updateFromPacket(PlayerPositionPacket p) {
		this.setX(p.getX());
		this.setY(p.getY());
		this.setZ(p.getZ());
	}

	/**
	 * Only to be invoked when entity is a player
	 */
	public void updateFromPacket(PlayerLookPacket p) {
		setYaw(p.getYaw());
		setPitch(p.getPitch());
	}

	/**
	 * Only to be invoked when entity is a player
	 */
	public void updateFromPacket(ServerboundPlayerPositionAndLookPacket p) {
		this.setX(p.getX());
		this.setY(p.getY());
		this.setZ(p.getZ());
		setYaw(p.getYaw());
		setPitch(p.getPitch());
	}
}
