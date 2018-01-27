package org.usfirst.frc.team85.robot;

import java.nio.ByteBuffer;
import edu.wpi.first.wpilibj.I2C;

public class RangeFinder {

	private I2C bus = new I2C(I2C.Port.kOnboard, Addresses.rangeFinder);
	
	public int getDistance() {
		bus.write(224, 81);
		ByteBuffer buffer = ByteBuffer.allocate(2);
		bus.read(225, 2, buffer);
		return buffer.getShort();
	}
	
}
