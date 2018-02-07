package org.usfirst.frc.team85.robot.auto.InputSource;

import java.util.ArrayList;

import org.usfirst.frc.team85.robot.Globals;
import org.usfirst.frc.team85.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroSource extends InputSource {

	private static ArrayList<Double> list = new ArrayList<Double>();

	private double[] temp = new double[] { 0, 0 };
	private double _heading;

	public GyroSource(double heading) {
		super();
		_heading = heading;
		setSetpoint(_heading + Globals.getInstance().getGyro().getAngle());
		setInputRange(-360, 360);
		setOutputRange(-1, 1);
		setPercentTolerance(3.0);
		enable();
	}

	@Override
	public double[] getCorrectionValues() {
		return temp;
	}

	@Override
	public boolean isSatisfied() {
		return false;
	}

	@Override
	protected double returnPIDInput() {
		double angle = Globals.getInstance().getGyro().getAngle();
		list.add(angle);
		SmartDashboard.putNumber("Gyro Angle", angle);
		return angle;
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("PID Outut", output);
		double error = getPIDController().getError();
		double correction = Math.abs(Math.sin(Math.toRadians(error)));
		Robot._diagnostics.log(error);

		if (error > 0) {
			temp[0] = 0;
			temp[1] = -correction;
		} else {
			temp[0] = -correction;
			temp[1] = 0;
		}
	}

	@Override
	protected void initDefaultCommand() {

	}
}
