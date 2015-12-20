package org.modularmc.network.packets.play.player;

import org.modularmc.io.PacketData;
import org.modularmc.network.Packet;

/**
 * @author Caspar Norée Palm
 */
public class PlayerAbilitiesPacket extends Packet {
	boolean isInCreative, canFly, isFlying;
	float flySpeed, walkSpeed;
	
	public boolean isInCreative() {
		return isInCreative;
	}

	public void setInCreative(boolean isInCreative) {
		this.isInCreative = isInCreative;
	}

	public boolean isCanFly() {
		return canFly;
	}

	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	public boolean isFlying() {
		return isFlying;
	}

	public void setFlying(boolean isFlying) {
		this.isFlying = isFlying;
	}

	public float getFlySpeed() {
		return flySpeed;
	}

	public void setFlySpeed(float flySpeed) {
		this.flySpeed = flySpeed;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}

	public void setWalkSpeed(float walkSpeed) {
		this.walkSpeed = walkSpeed;
	}

	public void write(PacketData data) {
		data.writeByte((byte) ((isInCreative ? 8 : 0) | (canFly ? 4 : 0) | (isFlying ? 2 : 0) | (isInCreative ? 1 : 0)));
		data.writeFloat(flySpeed);
		data.writeFloat(walkSpeed);
	}

	@Override
	public int getID() {
		return 0x39;
	}
}
