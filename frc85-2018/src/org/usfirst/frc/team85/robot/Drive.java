package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {

	private static MotorGroup _mgLeft = SuperStructure.getInstance().getMotorGroupLeft();
	private static MotorGroup _mgRight = SuperStructure.getInstance().getMotorGroupLeft();

	private static Joystick _leftJoystick = SuperStructure.getInstance().getLeftJoystick();
	private static Joystick _rightJoystick = SuperStructure.getInstance().getRightJoystick();

	public static void periodic() {
		double speedRight = 0;
		double speedLeft = 0;
		double power = (double) SmartDashboard.getNumber("Power", 1);
		double amplitude = (double) SmartDashboard.getNumber("Amplitude", .5);

		if (Math.abs(_rightJoystick.getRawAxis(1)) >= .1) {
			speedRight = Math.pow(_rightJoystick.getRawAxis(1), power);
		} else if (Math.abs(_rightJoystick.getRawAxis(1)) < .1) {
			speedRight = 0;
		}

		if (Math.abs(_leftJoystick.getRawAxis(1)) >= .1) {
			speedLeft = Math.pow(_leftJoystick.getRawAxis(1), power);
		} else if (Math.abs(_leftJoystick.getRawAxis(1)) < .1) {
			speedLeft = 0;
		}

		/*
		 * if (Math.abs(leftJoystick.getRawAxis(1)) <= .1) { speedLeft = 0; } else if
		 * (Math.abs(leftJoystick.getRawAxis(1)) < .9) { speedLeft = 2.5 *
		 * leftJoystick.getRawAxis(1) * leftJoystick.getRawAxis(1) - 1.25 *
		 * leftJoystick.getRawAxis(1) + .1; } else { speedLeft = 1; }
		 * 
		 * if (Math.abs(rightJoystick.getRawAxis(1)) <= .1) { speedRight = 0; } else if
		 * (Math.abs(rightJoystick.getRawAxis(1)) < .9) { speedRight = 2.5 *
		 * rightJoystick.getRawAxis(1) * rightJoystick.getRawAxis(1) - 1.25 *
		 * rightJoystick.getRawAxis(1) + .1; } else { speedRight = 1; }
		 */

		if (_rightJoystick.getRawButton(7)) {
			power = 1;
		}
		if (_rightJoystick.getRawButton(8)) {
			power = 3;
		}
		if (_leftJoystick.getRawButton(1)) {
			amplitude = .50;
		} else {
			amplitude = .25;
		}

		if (_rightJoystick.getRawButton(1) && Math.abs(_rightJoystick.getRawAxis(1)) > .1) {
			speedLeft = (_rightJoystick.getRawAxis(1));
			speedRight = (_rightJoystick.getRawAxis(1));

			if (_leftJoystick.getRawAxis(0) > .1) {
				if (_rightJoystick.getRawAxis(1) > 0) {
					speedRight = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * amplitude;
				} else {
					speedRight = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * amplitude;
				}
			} else if (_leftJoystick.getRawAxis(0) < -.1) {
				if (_rightJoystick.getRawAxis(1) > 0) {
					speedLeft = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * amplitude;
				} else {
					speedLeft = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * amplitude;
				}
			}
		}

		_mgRight.setPower(-speedRight);
		_mgLeft.setPower(-speedLeft);

		SmartDashboard.putNumber("Power", power);
		SmartDashboard.putNumber("Amplitude", amplitude);
		SmartDashboard.putNumber("RangeFinder", SuperStructure.getInstance().getRangeFinder().getDistance());
	}

}
