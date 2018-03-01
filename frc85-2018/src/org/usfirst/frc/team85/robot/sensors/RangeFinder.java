package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.Addresses;

import edu.wpi.first.wpilibj.I2C;

public class RangeFinder {

	private static RangeFinder instance = null;
	private I2C rangeFinderFront = new I2C(I2C.Port.kOnboard, Addresses.RANGEFINDER_FRONT);
	private I2C rangeFinderLeft = new I2C(I2C.Port.kOnboard, Addresses.RANGEFINDER_LEFT);
	private I2C rangeFinderRight = new I2C(I2C.Port.kOnboard, Addresses.RANGEFINDER_RIGHT);
	private I2C rangeFinderBack = new I2C(I2C.Port.kOnboard, Addresses.RANGEFINDER_BACK);
	private byte[] buffer = new byte[2];
	private Thread thread;
	private int rangeFront;
	private int rangeLeft;
	private int rangeRight;
	private int rangeBack;

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
					rangeFront = readSensor(rangeFinderFront, Addresses.RANGEFINDER_FRONT);
					rangeLeft = readSensor(rangeFinderLeft, Addresses.RANGEFINDER_LEFT);
					rangeRight = readSensor(rangeFinderRight, Addresses.RANGEFINDER_RIGHT);
					rangeBack = readSensor(rangeFinderBack, Addresses.RANGEFINDER_BACK);
				}
			}
		});
		thread.start();
	}

	private int readSensor(I2C device, int address) {
		try {
			device.write(address, 81);
			Thread.sleep(80);
			System.out.println("Read: " + device.read(address, 2, buffer));
			short msb = (short) (buffer[0] & 0x7F);
			short lsb = (short) (buffer[1] & 0xFF);
			return msb * 256 + lsb;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return -1;
	}

	public int getDistanceFront() {
		return rangeFront;
	}
	
	public int getDistanceLeft() {
		return rangeLeft;
	}
	
	public int getDistanceRight() {
		return rangeRight;
	}
	
	public int getDistanceBack() {
		return rangeBack;
	}
}