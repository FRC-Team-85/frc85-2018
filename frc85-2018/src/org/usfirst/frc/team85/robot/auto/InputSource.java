package org.usfirst.frc.team85.robot.auto;

public class InputSource {

	private InputType _type;
	private double[] _param;

	public InputSource(InputType type, double[] param) {
		_type = type;
		_param = param;

		switch (_type) {
		case ENCODER:
			// { }
			if (_param.length != 0) {
				Auto.terminate();
				System.err.println("ENCODER _param did not have adequate values");
			}
			break;
		case GYRO:
			// { heading }
			if (_param.length != 1) {
				Auto.terminate();
				System.err.println("GYRO _param did not have adequate values");
			}
			break;
		case RANGE:
			// { direction, distance }
			if (_param.length != 2) {
				Auto.terminate();
				System.err.println("RANGE _param did not have adequate values");
			}
			break;
		case VISION:
			// { }
			if (_param.length != 0) {
				Auto.terminate();
				System.err.println("VISION _param did not have adequate values");
			}
			break;
		default:
			break;
		}
	}

	public double[] getCorrectionValues() {
		double[] corrections = new double[2];
		// { left, right}

		switch (_type) {
		case ENCODER:
			// { }

			break;
		case GYRO:
			// { heading }

			break;
		case RANGE:
			// { direction, distance }

			break;
		case VISION:
			// { }

			break;
		default:
			break;
		}

		return corrections;
	}

	public boolean isSatisfied() {
		switch (_type) {
		case ENCODER:
			// { leftDistance, rightDistance }

			break;
		case RANGE:
			// { direction, distance }

			break;
		default:
			break;
		}

		return false;
	}
}
