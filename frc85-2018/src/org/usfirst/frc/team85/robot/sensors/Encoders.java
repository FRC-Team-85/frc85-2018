package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.Addresses;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoders {

	private static Encoders instance = null;

	private Encoder _leftDriveEncoder;
	private Encoder _rightDriveEncoder;

	private double wheelCircumference = .3333 * Math.PI;
	private double gearRatio = 2.04545; // one encoder revolution is 2.04545 wheel rotations, regardless of gear
	private double pulseperRevolution = 256.0;
	private double pulseWidth = wheelCircumference / gearRatio / pulseperRevolution / 2;

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

	private double getLeftDriveRate() {
		return -_leftDriveEncoder.getRate();
	}

	private double getRightDriveRate() {
		return _rightDriveEncoder.getRate();
	}

	public double getLeftVelocity() { // In feet/sec
		double velocity = getLeftDriveRate();
		SmartDashboard.putNumber("Left Velocity", velocity);
		return velocity;
	}

	public double getRightVelocity() { // In feet/sec
		double velocity = getRightDriveRate();
		SmartDashboard.putNumber("Right Velocity", velocity);
		return velocity;
	}

	public double getRightDistance() {
		return -_rightDriveEncoder.getDistance();
	}

	public double getLeftDistance() {
		return _leftDriveEncoder.getDistance();
	}
}
