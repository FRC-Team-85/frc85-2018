package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {

	private static MotorGroup _mgLeft = Globals.getInstance().getMotorGroupLeft();
	private static MotorGroup _mgRight = Globals.getInstance().getMotorGroupRight();

	private static Joystick _leftJoystick = Globals.getInstance().getLeftJoystick();
	private static Joystick _rightJoystick = Globals.getInstance().getRightJoystick();

	public static void periodic() {
		double speedRight = 0;
		double speedLeft = 0;
		double power = (double) SmartDashboard.getNumber("Power", 1);
		double amplitude = .35;

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

		if (_rightJoystick.getRawButton(7)) {
			power = 1;
		}
		if (_rightJoystick.getRawButton(8)) {
			power = 3;
		}

		if (_leftJoystick.getRawButton(1)) {
			amplitude = SmartDashboard.getNumber("High Amplitude", .65);
		} else {
			amplitude = SmartDashboard.getNumber("Low Amplitude", .35);
		}

		if (_rightJoystick.getRawButton(1) && Math.abs(_rightJoystick.getRawAxis(1)) > .1) {
			speedLeft = _rightJoystick.getRawAxis(1);
			speedRight = _rightJoystick.getRawAxis(1);

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
		// SmartDashboard.putNumber("RangeFinder",
		// SuperStructure.getInstance().getRangeFinder().getDistance());
	}

}
