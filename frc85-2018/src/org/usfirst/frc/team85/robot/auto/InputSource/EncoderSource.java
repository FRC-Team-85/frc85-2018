package org.usfirst.frc.team85.robot.auto.InputSource;

import org.usfirst.frc.team85.robot.SuperStructure;

public class EncoderSource extends InputSource {

	private double _distance;

	/** { distance } **/
	public EncoderSource(double distance) {
		super();
		_distance = distance;
	}

	@Override
	public double[] getCorrectionValues() {
		return null;
	}

	@Override
	public boolean isSatisfied() {
		if (SuperStructure.getInstance().getMotorGroupLeft().getDistance() > _distance) {
			return true;
		}
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
