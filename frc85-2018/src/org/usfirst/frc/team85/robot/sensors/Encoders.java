package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.Addresses;
import org.usfirst.frc.team85.robot.Globals;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoders {

	Globals globals = Globals.getInstance();

	private static Encoders instance = null;

	private static Encoder _leftDriveEncoder;
	private static Encoder _rightDriveEncoder;

	private static final double wheelDiameter = 4.0; // inches
	private static final double wheelCircumference = wheelDiameter * Math.PI; // ~12.57 inches
	private static final double gearRatio = 2.04545; // one encoder revolution is 2.04545 wheel rotations, regardless of
														// gear
	private static final double inchToFoot = 1.0 / 12.0;

	private Encoders() {
		_leftDriveEncoder = new Encoder(Addresses.ENCODERS_LEFT_A, Addresses.ENCODERS_LEFT_B);
		_rightDriveEncoder = new Encoder(Addresses.ENCODERS_RIGHT_A, Addresses.ENCODERS_RIGHT_B);

		_leftDriveEncoder.setDistancePerPulse(1.0 / 256.0); // One rotation is 256 pulses
		_rightDriveEncoder.setDistancePerPulse(1.0 / 256.0);
	}

	public static Encoders getInstance() {
		if (instance == null) {
			instance = new Encoders();
		}
		return instance;
	}

	public void driveEncoderReset() {
		_leftDriveEncoder.reset();
		_rightDriveEncoder.reset();
	}

	public double getLeftDriveRate() {
		return _leftDriveEncoder.getRate();
	}

	public double getRightDriveRate() {
		return _rightDriveEncoder.getRate();
	}

	public double getLeftVelocity() { // In feet/sec
		double rate = getLeftDriveRate();
		double velocity = wheelCircumference * gearRatio * inchToFoot * rate;
		SmartDashboard.putNumber("Left Velocity", velocity);
		return velocity;
	}

	public double getRightVelocity() { // In feet/sec
		double rate = getRightDriveRate();
		double velocity = wheelCircumference * gearRatio * inchToFoot * rate;
		SmartDashboard.putNumber("Right Velocity", velocity);
		return velocity;
	}
}
