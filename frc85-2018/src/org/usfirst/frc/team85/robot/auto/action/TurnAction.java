package org.usfirst.frc.team85.robot.auto.action;

import org.usfirst.frc.team85.robot.auto.InputSource.InputSource;

public class TurnAction extends Action {

	public TurnAction(InputSource firstSource, InputSource secondSource, InputSource terminationSource) {
		super(firstSource, secondSource, terminationSource);
	}

	@Override
	public double[] returnSpeed() {
		return new double[] { 0, 0 };
	}

}
