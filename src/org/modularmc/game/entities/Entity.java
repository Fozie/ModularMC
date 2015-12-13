package org.modularmc.game.entities;

import org.modularmc.game.world.World;

/**
 * @author Caspar Norée Palm
 */
public class Entity {
	private final World world;
	
	private double x,y,z;
	private float yaw, pitch; 
	
	public Entity(World world) {
		this.world = world;
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

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public World getWorld() {
		return world;
	}
}