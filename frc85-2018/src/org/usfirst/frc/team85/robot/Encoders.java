package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoders {

	private static Encoder _leftDriveEncoder = Globals.getInstance().getLeftDriveEncoder();
	private static Encoder _rightDriveEncoder = Globals.getInstance().getRightDriveEncoder();
	
	private static final int minRate = 10;
	
	public Encoders() {
		_leftDriveEncoder.setMinRate(minRate);
		_rightDriveEncoder.setMinRate(minRate);
	}
	
	public void driveEncoderReset() {
		_leftDriveEncoder.reset();
		_rightDriveEncoder.reset();
	}
	
	public double getLeftDriveEncoderRate() {
		// Returns the rate (units/sec) which is calculated with DistancePerPulse divided by the period.
		// https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599717-encoders-measuring-rotation-of-a-wheel-or-other-shaft
		SmartDashboard.putNumber("Left Drive Encoder Rate", _leftDriveEncoder.getRate());
		return Math.abs(_leftDriveEncoder.getRate());
	}
	
	public double getRightDriveEncoderRate() {
		SmartDashboard.putNumber("Right Drive Encoder Rate", _rightDriveEncoder.getRate());
		return Math.abs(_rightDriveEncoder.getRate());
	}
	
}
