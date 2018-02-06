package org.usfirst.frc.team85.robot.auto.InputSource;

import org.usfirst.frc.team85.robot.Globals;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroSource extends InputSource {

	private double[] temp = new double[2];
	private double _heading;

	public GyroSource(double heading) {
		super();
		_heading = heading;
		setSetpoint(_heading);
		enable();

	}

	@Override
	public double[] getCorrectionValues() {
		SmartDashboard.putString("Temp Gyro Corr Values", temp[0] + ":" + temp[1]);
		return temp;
	}

	@Override
	public boolean isSatisfied() {
		return false;
	}

	@Override
	protected double returnPIDInput() {
		// return Globals.getInstance().getIMU().getCompassHeading();
		double angle = Globals.getInstance().getGyro().getAngle();
		SmartDashboard.putNumber("Gyro Angle", angle);
		return angle;
	}

	@Override
	protected void usePIDOutput(double output) {
		double correction = Math.sin(Math.toRadians(output));
		SmartDashboard.putNumber("Gyro Correction Value", correction);

		if (correction > 0) {
			temp[0] = 0;
			temp[1] = correction;
		} else {
			temp[0] = correction;
			temp[1] = 0;
		}
	}

	@Override
	protected void initDefaultCommand() {

	}

}
