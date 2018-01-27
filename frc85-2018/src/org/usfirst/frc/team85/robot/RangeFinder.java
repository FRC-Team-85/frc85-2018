package org.usfirst.frc.team85.robot;

import java.nio.ByteBuffer;
import edu.wpi.first.wpilibj.I2C;

public class RangeFinder {

	private I2C bus = new I2C(I2C.Port.kOnboard, Addresses.rangeFinder);
	
	public boolean verify()
	{
		byte[] expected = new byte[] {(byte)0xA4, (byte)0x84};
		return bus.verifySensor(225, 2, expected);
	}
	
	public int getDistance() {
		ByteBuffer buffer = ByteBuffer.allocate(2);
		try {
			System.out.println("Write: " + bus.write(224, 81));
			
			Thread.sleep(100);
			System.out.println("Read: " + bus.read(225, 2, buffer));
			System.out.println("Bytes:");
			for (byte b : buffer.array()) {
				System.out.println(b);			
			}
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		
		buffer.rewind();
		
		return buffer.getShort();
	}
	
}
