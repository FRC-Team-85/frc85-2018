package org.usfirst.frc.team85.robot.auto.InputSource;

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
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {

	}

	@Override
	protected void initDefaultCommand() {

	}
}
