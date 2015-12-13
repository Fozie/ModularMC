package org.modularmc.utils;

/**
 * @author Caspar Norée Palm
 */
public class Vec3i {
	int x,y,z;

	public Vec3i(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vec3i() {
		this(0,0,0);
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	
	
}
