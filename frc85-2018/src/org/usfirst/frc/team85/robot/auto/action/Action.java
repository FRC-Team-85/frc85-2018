package org.usfirst.frc.team85.robot.auto.action;

import org.usfirst.frc.team85.robot.auto.Auto;
import org.usfirst.frc.team85.robot.auto.InputSource.InputSource;

public class Action {

	private InputSource _firstSource;
	private InputSource _secondSource;
	private InputSource _terminationSource;

	public Action(InputSource firstSource, InputSource secondSource, InputSource terminationSource) {
		_firstSource = firstSource;
		_secondSource = secondSource;
		_terminationSource = terminationSource;
	}

	public double[] returnSpeed() {
		return new double[] { 0, 0 };
	}

	public double[] getCorrections() {
		double[] output = new double[] { 0, 0 };

		double firstWeight = .75;
		double secondWeight = .25;

		if (_secondSource == null) {
			firstWeight = 1.0;
		}

		if (_firstSource == null) {
			secondWeight = 1.0;
		}

		if (_firstSource != null) {
			double[] corr1 = _firstSource.getCorrectionValues();
			output[0] += corr1[0] * firstWeight;
			output[1] += corr1[1] * firstWeight;

		}
		if (_secondSource != null) {
			double[] corr2 = _secondSource.getCorrectionValues();
			output[0] += corr2[0] * secondWeight;
			output[1] += corr2[1] * secondWeight;
		}

		return output;
	}

	public void checkSatisfaction() {
		if (_terminationSource != null && _terminationSource.isSatisfied()) {
			Auto.terminate();
		}
	}
}
