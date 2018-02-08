package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.I2C;

public class RangeFinder {

	private static RangeFinder instance = null;
	private I2C bus = new I2C(I2C.Port.kOnboard, Addresses.rangeFinder);
	private byte[] buffer = new byte[2];
	private Thread thread;
	private int range;

	public static RangeFinder getInstance() {
		if (instance == null) {
			instance = new RangeFinder();
		}

		return instance;
	}

	private RangeFinder() {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					range = readSensor();
				}
			}
		});
		thread.start();
	}

	private int readSensor() {
		try {
			bus.write(224, 81);
			Thread.sleep(80);
			System.out.println("Read: " + bus.read(225, 2, buffer));
			short msb = (short) (buffer[0] & 0x7F);
			short lsb = (short) (buffer[1] & 0xFF);
			return msb * 256 + lsb;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return -1;
	}

	public int getDistance() {
		return range;
	}
}