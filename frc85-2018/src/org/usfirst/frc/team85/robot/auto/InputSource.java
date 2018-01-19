package org.usfirst.frc.team85.robot.auto;

public class InputSource {

	private InputType _type;
	private double[] _param;

	// needs checks for param length
	public InputSource(InputType type, double[] param) {
		_type = type;
		_param = param;
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
}
