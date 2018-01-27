package org.usfirst.frc.team85.robot.auto;

import org.usfirst.frc.team85.robot.MotorGroup;
import org.usfirst.frc.team85.robot.SuperStructure;

public class InputSource {

	private InputType _type;
	private double[] _param;
	private PID _pid;

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
			_pid = new PID(_param[0]);

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

			double pid = _pid.getCorrection(SuperStructure.getInstance().getGyro().getAngle());

			if (pid > 0) {
				corrections[0] = 0;
				corrections[1] = 0;
			} else {
				corrections[0] = 0;
				corrections[1] = 0;
			}

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
		MotorGroup mgLeft = SuperStructure.getInstance().getMotorGroupLeft();
		MotorGroup mgRight = SuperStructure.getInstance().getMotorGroupRight();

		switch (_type) {
		case ENCODER:
			// { leftDistance, rightDistance }
			if (mgLeft.getDistance() == -1 || mgRight.getDistance() == -1) {
				return false;
			}

			if (mgLeft.getDistance() >= _param[0] || mgRight.getDistance() >= _param[1]) {
				return true;
			}

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
