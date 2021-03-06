package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.Addresses;

import edu.wpi.first.wpilibj.I2C;

public class RangeFinder {

	private static final double feetPerCentimeter = 0.0328084;

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
					try {
						triggerRead(rangeFinderFront, Addresses.RANGEFINDER_FRONT);
						triggerRead(rangeFinderLeft, Addresses.RANGEFINDER_LEFT);
						triggerRead(rangeFinderRight, Addresses.RANGEFINDER_RIGHT);
						triggerRead(rangeFinderBack, Addresses.RANGEFINDER_BACK);
						Thread.sleep(80);
						rangeFront = readSensor(rangeFinderFront, Addresses.RANGEFINDER_FRONT);
						rangeLeft = readSensor(rangeFinderLeft, Addresses.RANGEFINDER_LEFT);
						rangeRight = readSensor(rangeFinderRight, Addresses.RANGEFINDER_RIGHT);
						rangeBack = readSensor(rangeFinderBack, Addresses.RANGEFINDER_BACK);
					} catch (Exception ex) {
						System.out.println("Error in range finder loop: " + ex.toString());
					}
				}
			}
		});
		thread.start();
	}

	private void triggerRead(I2C device, int address) {
		try {
			device.write(address, 81);
		} catch (Exception ex) {
			System.out.println("Error triggering range finder at address '" + address + "': " + ex.toString());
		}
	}

	private int readSensor(I2C device, int address) {
		try {
			if (device.read(address, 2, buffer)) {
				return -2;
			}

			short msb = (short) (buffer[0] & 0x7F);
			short lsb = (short) (buffer[1] & 0xFF);
			return msb * 256 + lsb;
		} catch (Exception ex) {
			System.out.println("Error reading value from range finder at address '" + address + "': " + ex.toString());
		}

		return -1;
	}

	public double getDistanceFront() {
		return rangeFront * feetPerCentimeter;
	}

	public double getDistanceLeft() {
		return rangeLeft * feetPerCentimeter;
	}

	public double getDistanceRight() {
		return rangeRight * feetPerCentimeter;
	}

	public double getDistanceBack() {
		return rangeBack * feetPerCentimeter;
	}
}