package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {

	protected static Drive instance = null;

	private MotorGroup _mgLeft = Globals.getInstance().getMotorGroupLeft();
	private MotorGroup _mgRight = Globals.getInstance().getMotorGroupRight();

	private Joystick _leftJoystick = Globals.getInstance().getLeftJoystick();
	private Joystick _rightJoystick = Globals.getInstance().getRightJoystick();

	private double _speedRight = 0;
	private double _speedLeft = 0;

	private double _power = (double) SmartDashboard.getNumber("Power", 1);
	private double _amplitude = .35;

	/**
	 * Singleton for Drive class
	 * 
	 * @return The single instance of Drive
	 */
	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}

	/**
	 * Called throughout teleop
	 */
	public void periodic() {

		powerButtons();

		if (_rightJoystick.getRawButton(1)) {
			fpsDrive();
		}
		else {
			tankDrive();
		}

		_mgRight.setPower(-_speedRight);
		_mgLeft.setPower(-_speedLeft);

		SmartDashboard.putNumber("Power", _power);
		SmartDashboard.putNumber("High Amplitude", .65);
		SmartDashboard.putNumber("Low Amplitude", .35);
		// SmartDashboard.putNumber("RangeFinder",global.getRangeFinder().getDistance());
	}

	/**
	 * Primary drive mode
	 */
	private void tankDrive() {
		if (Math.abs(_rightJoystick.getRawAxis(1)) >= .1) {
			_speedRight = Math.pow(_rightJoystick.getRawAxis(1), _power);
		} else if (Math.abs(_rightJoystick.getRawAxis(1)) < .1) {
			_speedRight = 0;
		}

		if (Math.abs(_leftJoystick.getRawAxis(1)) >= .1) {
			_speedLeft = Math.pow(_leftJoystick.getRawAxis(1), _power);
		} else if (Math.abs(_leftJoystick.getRawAxis(1)) < .1) {
			_speedLeft = 0;
		}
	}

	/**
	 * A modifier to TankDrive, not a drive mode in itself(?)
	 */
	private void fpsDrive() {
		if (Math.abs(_rightJoystick.getRawAxis(1)) > .1) {
			_speedLeft = _rightJoystick.getRawAxis(1);
			_speedRight = _rightJoystick.getRawAxis(1);

			if (_leftJoystick.getRawAxis(0) > .1) {
				if (_rightJoystick.getRawAxis(1) > 0) {
					_speedRight = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * _amplitude;
				} else {
					_speedRight = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * _amplitude;
				}
			} else if (_leftJoystick.getRawAxis(0) < -.1) {
				if (_rightJoystick.getRawAxis(1) > 0) {
					_speedLeft = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * _amplitude;
				} else {
					_speedLeft = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * _amplitude;
				}
			}
		}
	}

	/**
	 * Modifies the power/amp of joystick inputs
	 */
	private void powerButtons() {
		if (_rightJoystick.getRawButton(7)) {
			_power = 1;
		}
		if (_rightJoystick.getRawButton(8)) {
			_power = 3;
		}
		if (_leftJoystick.getRawButton(1)) {
			_amplitude = SmartDashboard.getNumber("High Amplitude", .65);
		} else {
			_amplitude = SmartDashboard.getNumber("Low Amplitude", .35);
		}
	}

}
