package org.usfirst.frc.team85.robot.auto.InputSource;

import org.usfirst.frc.team85.robot.SuperStructure;

public class RangeSource extends InputSource {

	private byte[] _buffer = new byte[4];

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
		SuperStructure.getInstance().getI2C().readOnly(_buffer, 4);
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {

	}

	@Override
	protected void initDefaultCommand() {

	}
}
