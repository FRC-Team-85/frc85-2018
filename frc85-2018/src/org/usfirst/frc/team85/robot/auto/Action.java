package org.usfirst.frc.team85.robot.auto;

public class Action {

	private ActionType _type;
	private double[] _param;
	private InputSource _firstSource;
	private InputSource _secondSource;

	// needs checks for param length
	public Action(ActionType type, double[] param, InputSource firstSource, InputSource secondSource) {
		_type = type;
		_param = param;
		_firstSource = firstSource;
		_secondSource = secondSource;
	}

	public double[] returnSpeed() {
		double[] output = new double[2];
		// { left, right}

		switch (_type) {
		case ACCEL:
			// { start, stop, increment }

			if (_param.length != 3) {
				Auto.terminate();
				System.err.println("ACCEL _param did not have enough values");
			}

			if (_param[0] >= _param[1]) {
				Auto.terminate();
			}

			_param[0] = output[0] = output[1] = _param[0] + _param[2];
			break;
		case DECEL:
			// { start, stop, increment }

			if (_param.length != 3) {
				Auto.terminate();
				System.err.println("DECEL _param did not have enough values");
			}

			if (_param[0] <= _param[1]) {
				Auto.terminate();
			}

			_param[0] = output[0] = output[1] = _param[0] - _param[2];

			break;
		case STRAIGHT:
			// { speed, duration }

			if (_param.length != 2) {
				Auto.terminate();
				System.err.println("STRAIGHT _param did not have enough values");
			}

			// if (encoder.getCount() >= duration) {
			// Auto.terminate();
			// }

			output[0] = output[1] = _param[0];

			break;
		case TURN:
			// { angle, radius}

			if (_param.length != 2) {
				Auto.terminate();
				System.err.println("TURN _param did not have enough values");
			}

			// turn code
			break;
		case ELEVATOR:
			// { setting }

			// manage concurrent actions
			break;
		case BAR4:
			// { boolean 0 or 1 }

			// manage concurrent actions
			break;
		default:

			break;
		}

		if (_firstSource != null) {
			double[] corr1 = _firstSource.getCorrectionValues();
			output[0] += corr1[0] * .75;
			output[1] += corr1[1] * .75;

		}
		if (_secondSource != null) {
			double[] corr2 = _secondSource.getCorrectionValues();
			output[0] += corr2[0] * .25;
			output[1] += corr2[1] * .25;

		}

		return output;
	}
}
