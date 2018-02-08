package org.usfirst.frc.team85.robot.auto.InputSource;

import org.usfirst.frc.team85.robot.RangeFinder;

public class RangeSource extends InputSource {

	private double[] temp = new double[] { 0, 0 };

	private double _distance = 0;

	public RangeSource(double distance) {
		super();
		_distance = distance;
		setSetpoint(_distance);
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
		return RangeFinder.getInstance().getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		double error = getPIDController().getError();
		double correction = Math.abs(error);

		temp[0] = -correction;
		temp[1] = 0;
	}

	@Override
	protected void initDefaultCommand() {

	}
}
