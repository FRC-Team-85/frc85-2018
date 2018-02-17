package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.CompressorActive;
import org.usfirst.frc.team85.robot.commands.ToggleCamera;
import org.usfirst.frc.team85.robot.commands.ToggleTransmission;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinDegrees;
import org.usfirst.frc.team85.robot.commands.gripper.ToggleGripper;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

	private static OI _instance;

	private Joystick _leftJoystick;
	private Joystick _rightJoystick;
	private Joystick _liftOperatorStation;
	private Joystick _miscOperatorStation;

	private OI() {
		_leftJoystick = new Joystick(Addresses.LEFT_JOYSTICK);
		_rightJoystick = new Joystick(Addresses.RIGHT_JOYSTICK);
		_liftOperatorStation = new Joystick(Addresses.OPERATOR_STATION_LIFT);
		_miscOperatorStation = new Joystick(Addresses.OPERATOR_STATION_MISC);

		JoystickButton leftButton2 = new JoystickButton(_leftJoystick, 2);
		JoystickButton leftButton3 = new JoystickButton(_leftJoystick, 3);
		JoystickButton leftButton4 = new JoystickButton(_leftJoystick, 4);
		JoystickButton leftButton5 = new JoystickButton(_leftJoystick, 5);
		JoystickButton leftButton10 = new JoystickButton(_leftJoystick, 10);

		leftButton2.whenPressed(new SpinDegrees(-180));
		leftButton3.whenPressed(new SpinDegrees(360));
		leftButton4.whenPressed(new SpinDegrees(90));
		leftButton5.whenPressed(new SpinDegrees(-90));
		leftButton10.whenPressed(new ToggleCamera());

		JoystickButton rightButton2 = new JoystickButton(_rightJoystick, 2);

		rightButton2.whenPressed(new ToggleTransmission());

		JoystickButton liftSwitchButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_SWITCH);
		JoystickButton liftGroundButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_GROUND);
		JoystickButton liftPlatformButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_PLATFORM);
		JoystickButton liftLowScaleButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_LOW_SCALE);
		JoystickButton liftMediumScaleButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_MEDIUM_SCALE);
		JoystickButton liftHighScaleButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_HIGH_SCALE);
		JoystickButton liftDoubleScaleButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_DOUBLE_SCALE);

		JoystickButton gripperButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_TOGGLE_GRIPPER);
		JoystickButton unknownButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_UNKNOWN);
		JoystickButton intakeForwardButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_INTAKE_FORWARD);
		JoystickButton intakeReverseButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_INTAKE_REVERSE);
		JoystickButton compressorOnButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_COMPRESSOR_ON);
		JoystickButton compressorOffButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_COMPRESSOR_OFF);
		JoystickButton leftChickenWingButton = new JoystickButton(_miscOperatorStation,
				Addresses.OS_MISC_LEFT_CHICKEN_WING);
		JoystickButton rightChickenWingButton = new JoystickButton(_miscOperatorStation,
				Addresses.OS_MISC_RIGHT_CHICKEN_WING);

		gripperButton.whenPressed(new ToggleGripper());
		compressorOnButton.whenPressed(new CompressorActive(true));
		compressorOffButton.whenPressed(new CompressorActive(false));

		SmartDashboard.putNumber("High Amplitude", .65);
		SmartDashboard.putNumber("Low Amplitude", .35);
	}

	public static OI getInstance() {
		if (_instance == null) {
			_instance = new OI();
		}
		return _instance;
	}

	// public double[] getSpeedInput() {
	// double _speedRight = 0;
	// double _speedLeft = 0;
	// double power = (double) SmartDashboard.getNumber("Power", 1);
	// double amplitude = .35;
	//
	// if (Math.abs(_rightJoystick.getRawAxis(1)) >= .1) {
	// _speedRight = Math.pow(_rightJoystick.getRawAxis(1), power);
	// } else if (Math.abs(_rightJoystick.getRawAxis(1)) < .1) {
	// _speedRight = 0;
	// }
	//
	// if (Math.abs(_leftJoystick.getRawAxis(1)) >= .1) {
	// _speedLeft = Math.pow(_leftJoystick.getRawAxis(1), power);
	// } else if (Math.abs(_leftJoystick.getRawAxis(1)) < .1) {
	// _speedLeft = 0;
	// }
	//
	// if (_rightJoystick.getRawButton(7)) {
	// power = 1;
	// }
	// if (_rightJoystick.getRawButton(8)) {
	// power = 3;
	// }
	//
	// if (_leftJoystick.getRawButton(1)) {
	// amplitude = SmartDashboard.getNumber("High Amplitude", .65);
	// } else {
	// amplitude = SmartDashboard.getNumber("Low Amplitude", .35);
	// }
	//
	// if (_rightJoystick.getRawButton(1) && Math.abs(_rightJoystick.getRawAxis(1))
	// > .1) {
	// _speedLeft = _rightJoystick.getRawAxis(1);
	// _speedRight = _rightJoystick.getRawAxis(1);
	//
	// if (_leftJoystick.getRawAxis(0) > .1) {
	// if (_rightJoystick.getRawAxis(1) > 0) {
	// _speedRight = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) *
	// amplitude;
	// } else {
	// _speedRight = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) *
	// amplitude;
	// }
	// } else if (_leftJoystick.getRawAxis(0) < -.1) {
	// if (_rightJoystick.getRawAxis(1) > 0) {
	// _speedLeft = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) *
	// amplitude;
	// } else {
	// _speedLeft = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) *
	// amplitude;
	// }
	// }
	// }
	//
	// SmartDashboard.putNumber("Power", power);
	//
	// return new double[] { -_speedLeft, -_speedRight };
	// }

	private double _speedLeft = 0, _speedRight = 0, _power = 1, _amplitude = .35;

	public double[] getSpeedInput() {

		powerButtons();

		if (isFPS()) {
			fpsDrive();
		} else {
			tankDrive();
		}

		autoTrans();

		SmartDashboard.putNumber("Power", _power);

		return new double[] { -_speedLeft, -_speedRight };
	}

	/**
	 * Right joystick sets right side, left joystick sets left side
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
	 * Right joystick sets forward/back speed, left joystick turns
	 */
	private void fpsDrive() {
		if (Math.abs(_rightJoystick.getRawAxis(1)) > .1) {
			_speedLeft = _rightJoystick.getRawAxis(1);
			_speedRight = _rightJoystick.getRawAxis(1);
		}

		if (_rightJoystick.getRawButton(1) && Math.abs(_rightJoystick.getRawAxis(1)) > .1) {
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

	/**
	 * Sets transmission automatically
	 */
	private void autoTrans() {
		double leftFrontCurrent = Globals.getInstance().getPDP().getCurrent(Addresses.DRIVETRAIN_LEFT_FRONT_MOTOR);
		double leftBackCurrent = Globals.getInstance().getPDP().getCurrent(Addresses.DRIVETRAIN_LEFT_BACK_MOTOR);

		double rightFrontCurrent = Globals.getInstance().getPDP().getCurrent(Addresses.DRIVETRAIN_RIGHT_FRONT_MOTOR);
		double rightBackCurrent = Globals.getInstance().getPDP().getCurrent(Addresses.DRIVETRAIN_RIGHT_BACK_MOTOR);

		if (Math.abs(leftFrontCurrent + leftBackCurrent + rightFrontCurrent + rightBackCurrent) > 150) {
			DriveTrain.getInstance().setHighGear(false);
		} else {
			DriveTrain.getInstance().setHighGear(true);
		}
	}

	public boolean isFPS() {
		return _rightJoystick.getRawButton(1);
	}
}
