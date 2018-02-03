package org.usfirst.frc.team85.robot.auto.action;

import org.usfirst.frc.team85.robot.auto.InputSource.InputSource;

public class DriveStraightAction extends Action {

	private double _speed;

	public DriveStraightAction(double speed, InputSource firstSource, InputSource secondSource,
			InputSource terminationSource) {
		super(firstSource, secondSource, terminationSource);
		_speed = speed;
	}

	@Override
	public double[] returnSpeed() {
		checkSatisfaction();

		double[] speed = new double[2];
		double[] corrections = getCorrections();

		speed[0] = _speed += corrections[0];
		speed[1] = _speed += corrections[0];

		return speed;
	}

}
