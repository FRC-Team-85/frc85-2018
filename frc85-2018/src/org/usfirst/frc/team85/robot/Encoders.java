package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoders {
	
	Globals globals = Globals.getInstance();
	
	private static Encoder _leftDriveEncoder = Globals.getInstance().getLeftDriveEncoder();
	private static Encoder _rightDriveEncoder = Globals.getInstance().getRightDriveEncoder();
	
	private static double wheelDiameter = 4; // inches
	private static double wheelCircumference = wheelDiameter * Math.PI; // ~12.57 inches
	
	private static double gearRatio = 2.04545; // one encoder revolution is 2.04545 wheel rotations, regardless of gear
	
	private static double inchToFoot = 1/12;
	
	public Encoders() {
		_leftDriveEncoder.setDistancePerPulse(1/256); //One rotation is 256 pulses
		_rightDriveEncoder.setDistancePerPulse(1/256);
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
