package org.modularmc.game.entities;

import org.modularmc.game.world.World;

/**
 * @author Caspar Norée Palm
 */
public abstract class Entity {
	private final World world;
	
	private EntityLocation location;
	
	private boolean alive;
	
	private int age; // The age of the entity in ticks.
	
	public Entity(World world) {
		this.world = world;
		this.location = new EntityLocation();
	}
	
	public void tick() {
		
	}
	
	public EntityLocation getLocation() {
		return location;
	}
	
	public double getX() {
		return location.getX();
	}

	public void setX(double x) {
		location.setX(x);
	}

	public double getY() {
		return location.getX();
	}

	public void setY(double y) {
		location.setY(y);
	}

	public double getZ() {
		return location.getZ();
	}

	public void setZ(double z) {
		location.setZ(z);
	}

	public float getYaw() {
		return location.getYaw();
	}

	public void setYaw(float yaw) {
		location.setYaw(yaw);
	}

	public float getPitch() {
		return location.getPitch();
	}

	public void setPitch(float pitch) {
		location.setPitch(pitch);
	}

	public World getWorld() {
		return world;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean active) {
		this.alive = active;
	}
}