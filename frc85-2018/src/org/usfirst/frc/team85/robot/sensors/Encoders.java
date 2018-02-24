package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.Addresses;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoders {

	private static Encoders instance = null;

	private Encoder _leftDriveEncoder;
	private Encoder _rightDriveEncoder;

	private double wheelDiameter = 4.0; // inches
	private double wheelCircumference = wheelDiameter * Math.PI; // ~12.57 inches
	private double gearRatio = 2.04545; // one encoder revolution is 2.04545 wheel rotations, regardless of
										// gear
	private static final double inchToFoot = 0.083333;
	private double pulseperRevolution = 256.0;
	private double pulseWidth = wheelCircumference / gearRatio / pulseperRevolution;

	private Encoders() {
		_leftDriveEncoder = new Encoder(Addresses.ENCODERS_LEFT_A, Addresses.ENCODERS_LEFT_B, false,
				Encoder.EncodingType.k4X);
		_rightDriveEncoder = new Encoder(Addresses.ENCODERS_RIGHT_A, Addresses.ENCODERS_RIGHT_B, false,
				Encoder.EncodingType.k4X);

		_leftDriveEncoder.setDistancePerPulse(pulseWidth); // One rotation is 256 pulses
		_rightDriveEncoder.setDistancePerPulse(pulseWidth);
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
		double velocity = getLeftDriveRate() * inchToFoot;
		SmartDashboard.putNumber("Left Velocity", velocity);
		return velocity;
	}

	public double getRightVelocity() { // In feet/sec
		double velocity = getRightDriveRate() * inchToFoot;
		SmartDashboard.putNumber("Right Velocity", velocity);
		return velocity;
	}
}
