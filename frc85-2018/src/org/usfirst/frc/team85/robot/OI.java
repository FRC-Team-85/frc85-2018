package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.SpinDegrees;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

	private static OI _instance;

	private Joystick _leftJoystick;
	private Joystick _rightJoystick;

	private OI() {
		_leftJoystick = new Joystick(Addresses.LEFT_JOYSTICK);
		_rightJoystick = new Joystick(Addresses.RIGHT_JOYSTICK);

		JoystickButton leftButton2 = new JoystickButton(_leftJoystick, 2);
		JoystickButton leftButton3 = new JoystickButton(_leftJoystick, 3);
		JoystickButton leftButton4 = new JoystickButton(_leftJoystick, 4);
		JoystickButton leftButton5 = new JoystickButton(_leftJoystick, 5);

		leftButton2.whenPressed(new SpinDegrees(-180));
		leftButton3.whenPressed(new SpinDegrees(360));
		leftButton4.whenPressed(new SpinDegrees(90));
		leftButton5.whenPressed(new SpinDegrees(-90));
	}

	public static OI getInstance() {
		if (_instance == null) {
			_instance = new OI();
		}
		return _instance;
	}

	public double[] getSpeedInput() {
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

		SmartDashboard.putNumber("Power", power);

		return new double[] { -speedLeft, -speedRight };
	}

	public boolean isFPS() {
		return _rightJoystick.getRawButton(1);
	}
}
