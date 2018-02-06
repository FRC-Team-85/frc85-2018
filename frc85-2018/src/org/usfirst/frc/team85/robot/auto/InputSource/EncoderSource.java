package org.usfirst.frc.team85.robot.auto.InputSource;

import org.usfirst.frc.team85.robot.Globals;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderSource extends InputSource {

	private double _distance;

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
		if (Globals.getInstance().getMotorGroupLeft().getDistance() > _distance
				|| Globals.getInstance().getMotorGroupRight().getDistance() > _distance) {
			SmartDashboard.putNumber("MG Left Distance", Globals.getInstance().getMotorGroupLeft().getDistance());
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
