package org.usfirst.frc.team85.robot.auto;

public class Action {

	private ActionType _type;
	private double[] _param;
	private InputSource _firstSource;
	private InputSource _secondSource;
	private InputSource _terminationSource;

	public Action(ActionType type, double[] param, InputSource firstSource, InputSource secondSource,
			InputSource terminationSource) {
		_type = type;
		_param = param;
		_firstSource = firstSource;
		_secondSource = secondSource;
		_terminationSource = terminationSource;

		switch (_type) {
		case ACCEL:
			if (_param.length != 3) {
				Auto.terminate();
				System.err.println("ACCEL _param did not have adequate values");
			}
			break;
		case DECEL:
			if (_param.length != 3) {
				Auto.terminate();
				System.err.println("DECEL _param did not have adequate values");
			}
			break;
		case STRAIGHT:
			if (_param.length != 2) {
				Auto.terminate();
				System.err.println("STRAIGHT _param did not have adequate values");
			}
			break;
		case TURN:
			if (_param.length != 2) {
				Auto.terminate();
				System.err.println("TURN _param did not have adequate values");
			}
			break;
		}
	}

	public double[] returnSpeed() {
		double[] output = new double[2];
		// { left, right}

		if (_terminationSource != null && _terminationSource.isSatisfied()) {
			Auto.terminate();
			return null;
		}

		switch (_type) {
		case ACCEL:
			// { start, stop, increment(?) }

			if (_param[0] >= _param[1]) {
				Auto.terminate();
			}

			_param[0] = output[0] = output[1] = _param[0] + _param[2];
			break;
		case DECEL:
			// { start, stop, increment }

			if (_param[0] <= _param[1]) {
				Auto.terminate();
			}

			_param[0] = output[0] = output[1] = _param[0] - _param[2];

			break;
		case STRAIGHT:
			// { speed }

			output[0] = output[1] = _param[0];

			break;
		case TURN:
			// { angle, radius}

			break;
		default:
			break;
		}

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
}
