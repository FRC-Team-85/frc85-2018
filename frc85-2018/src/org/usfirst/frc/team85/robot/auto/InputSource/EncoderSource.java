package org.usfirst.frc.team85.robot.auto.InputSource;

public class EncoderSource extends InputSource {

	public EncoderSource(double distance) {
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
