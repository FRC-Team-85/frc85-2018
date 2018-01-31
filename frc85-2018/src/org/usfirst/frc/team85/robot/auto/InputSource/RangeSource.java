package org.usfirst.frc.team85.robot.auto.InputSource;

import org.usfirst.frc.team85.robot.SuperStructure;

public class RangeSource extends InputSource {

	public RangeSource() {
		super();
	}

	@Override
	public double[] getCorrectionValues() {

		return null;
	}

	@Override
	public boolean isSatisfied() {

		return false;
	}

	@Override
	protected double returnPIDInput() {
		return SuperStructure.getInstance().getRangeFinder().getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {

	}

	@Override
	protected void initDefaultCommand() {

	}
}
