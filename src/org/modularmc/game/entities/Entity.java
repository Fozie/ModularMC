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
}