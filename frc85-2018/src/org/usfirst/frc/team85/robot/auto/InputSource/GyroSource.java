package org.usfirst.frc.team85.robot.auto.InputSource;

import org.usfirst.frc.team85.robot.SuperStructure;

public class GyroSource extends InputSource {

	private double[] temp = new double[2];
	private double _heading;

	public GyroSource(double heading) {
		super();
		_heading = heading;
		setSetpoint(_heading);
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
		return SuperStructure.getInstance().getGyro().getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		double correction = Math.sin(output / 360 * Math.PI);

		if (correction > 0) {
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
