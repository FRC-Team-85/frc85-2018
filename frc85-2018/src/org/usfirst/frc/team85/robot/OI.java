package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.CancelCubeSearch;
import org.usfirst.frc.team85.robot.commands.CancelEjectCube;
import org.usfirst.frc.team85.robot.commands.CompressorActive;
import org.usfirst.frc.team85.robot.commands.CubeSearch;
import org.usfirst.frc.team85.robot.commands.EjectCube;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinExactDegrees;
import org.usfirst.frc.team85.robot.commands.drivetrain.ToggleTransmission;
import org.usfirst.frc.team85.robot.commands.gripper.ToggleGripper;
import org.usfirst.frc.team85.robot.commands.intake.ActivateIntake;
import org.usfirst.frc.team85.robot.commands.intake.ToggleProtectIntake;
import org.usfirst.frc.team85.robot.commands.lift.LockLift;
import org.usfirst.frc.team85.robot.commands.lift.MoveLift;
import org.usfirst.frc.team85.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

	private static OI _instance;

	private Joystick _leftJoystick;
	private Joystick _rightJoystick;
	private Joystick _liftOperatorStation;
	private Joystick _miscOperatorStation;

	private Command _liftUp, _liftDown, _liftStop;
	private boolean _liftStopped = false;

	private double _speedLeft = 0, _speedRight = 0, _power = 1, _turningAmplitude = 0;

	private OI() {
		_leftJoystick = new Joystick(Addresses.LEFT_JOYSTICK);
		_rightJoystick = new Joystick(Addresses.RIGHT_JOYSTICK);
		_liftOperatorStation = new Joystick(Addresses.OPERATOR_STATION_LIFT);
		_miscOperatorStation = new Joystick(Addresses.OPERATOR_STATION_MISC);

		_liftUp = new MoveLift(Variables.getInstance().getLiftManualSpeed());
		_liftDown = new MoveLift(-Variables.getInstance().getLiftManualSpeed());
		_liftStop = new MoveLift(0);

		JoystickButton turnLeft = new JoystickButton(_leftJoystick, 4);
		JoystickButton turnRight = new JoystickButton(_leftJoystick, 5);
		JoystickButton turnAround = new JoystickButton(_leftJoystick, 3);

		turnLeft.whenPressed(new SpinExactDegrees(90));
		turnRight.whenPressed(new SpinExactDegrees(-90));
		turnAround.whenPressed(new SpinExactDegrees(180));

		JoystickButton manualTrans = new JoystickButton(_leftJoystick, 2);
		manualTrans.whenPressed(new ToggleTransmission());

		JoystickButton cubeSearchDriverButton = new JoystickButton(_rightJoystick, 2);
		cubeSearchDriverButton.whenPressed(new CubeSearch());
		cubeSearchDriverButton.whenReleased(new CancelCubeSearch());

		JoystickButton liftPlatformSwitchButton = new JoystickButton(_liftOperatorStation,
				Addresses.OS_LIFT_PLATFORM_SWITCH);
		JoystickButton liftGroundButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_GROUND);
		JoystickButton liftLockButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_LOCK);
		JoystickButton liftLowScaleButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_LOW_SCALE);
		JoystickButton liftMediumScaleButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_MEDIUM_SCALE);
		JoystickButton liftHighScaleButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_HIGH_SCALE);
		JoystickButton liftDoubleScaleButton = new JoystickButton(_liftOperatorStation, Addresses.OS_LIFT_DOUBLE_SCALE);
		JoystickButton liftClimbButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_LIFT_CLIMB);

		liftGroundButton.whenPressed(new SetLiftHeight(Variables.LIFT_GROUND));
		liftPlatformSwitchButton.whenPressed(new SetLiftHeight(Variables.LIFT_SWITCH));
		liftLowScaleButton.whenPressed(new SetLiftHeight(Variables.LIFT_SCALE_LOW));
		liftMediumScaleButton.whenPressed(new SetLiftHeight(Variables.LIFT_SCALE));
		liftHighScaleButton.whenPressed(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		liftDoubleScaleButton.whenPressed(new SetLiftHeight(Variables.LIFT_SCALE_HIGH_DOUBLE));
		liftClimbButton.whenPressed(new SetLiftHeight(Variables.LIFT_CLIMB));

		liftLockButton.whenPressed(new LockLift(true));
		liftLockButton.whenReleased(new LockLift(false));

		JoystickButton gripperButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_TOGGLE_GRIPPER);
		JoystickButton protectButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_INTAKE_PROTECT);
		JoystickButton intakeForwardButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_INTAKE_FORWARD);
		JoystickButton intakeReverseButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_INTAKE_REVERSE);
		JoystickButton compressorOnButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_COMPRESSOR_ON);
		JoystickButton compressorOffButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_COMPRESSOR_OFF);
		JoystickButton searchCubeButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_CUBE_SEARCH);
		JoystickButton ejectCubeButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_EXCHANGE_BUTTON);

		gripperButton.whenPressed(new ToggleGripper());
		protectButton.whenPressed(new ToggleProtectIntake());

		intakeForwardButton.whenPressed(new ActivateIntake(1.0));
		intakeForwardButton.whenReleased(new ActivateIntake(0.0));
		intakeReverseButton.whenPressed(new ActivateIntake(-1.0));
		intakeReverseButton.whenReleased(new ActivateIntake(0.0));

		compressorOnButton.whenPressed(new CompressorActive(true));
		compressorOffButton.whenPressed(new CompressorActive(false));

		searchCubeButton.whenPressed(new CubeSearch());
		searchCubeButton.whenReleased(new CancelCubeSearch());

		ejectCubeButton.whenPressed(new EjectCube());
		ejectCubeButton.whenReleased(new CancelEjectCube());
	}

	public void periodic() {
		double joystick = _liftOperatorStation.getRawAxis(1);

		if (joystick == -1) {
			_liftUp.start();
			_liftStopped = false;
		} else if (joystick == 1) {
			_liftDown.start();
			_liftStopped = false;
		} else if (!_liftStopped) {
			_liftStop.start();
			_liftStopped = true;
		}
	}

	public static OI getInstance() {
		if (_instance == null) {
			_instance = new OI();
		}
		return _instance;
	}

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
		double rightStick = _rightJoystick.getRawAxis(1);
		double leftStick = _leftJoystick.getRawAxis(1);

		if (Math.abs(rightStick) >= .2) {
			_speedRight = Math.pow(rightStick, _power)
					+ Variables.getInstance().getUsefulDriveTrainPower() * (Math.abs(rightStick) / rightStick);
		} else if (Math.abs(rightStick) < .2) {
			_speedRight = 0;
		}

		if (Math.abs(leftStick) >= .2) {
			_speedLeft = Math.pow(leftStick, _power)
					+ Variables.getInstance().getUsefulDriveTrainPower() * (Math.abs(leftStick) / leftStick);
		} else if (Math.abs(leftStick) < .2) {
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
					_speedRight = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * _turningAmplitude;
				} else {
					_speedRight = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * _turningAmplitude;
				}

			} else if (_leftJoystick.getRawAxis(0) < -.1) {
				if (_rightJoystick.getRawAxis(1) > 0) {
					_speedLeft = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * _turningAmplitude;
				} else {
					_speedLeft = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * _turningAmplitude;
				}
			}
		}
	}

	/**
	 * Modifies the power/amp of joystick inputs
	 */
	private void powerButtons() {
		if (_rightJoystick.getRawButton(7)) {
			_power = Variables.getInstance().getLowJoystickPower();
		}
		if (_rightJoystick.getRawButton(8)) {
			_power = Variables.getInstance().getHighJoystickPower();
		}
		if (_leftJoystick.getRawButton(1)) {
			_turningAmplitude = Variables.getInstance().getTurningHighAmplitude();
		} else {
			_turningAmplitude = Variables.getInstance().getTurningLowAmplitude();
		}
	}

	/**
	 * Sets transmission automatically
	 */
	private void autoTrans() {
		if (Math.abs(DriveTrain.getInstance().getTotalCurrent()) > Variables.getInstance()
				.getDriveTrainCurrentThreshold()) {
			DriveTrain.getInstance().setHighGear(false);
		} else if (Math.abs(Encoders.getInstance().getLeftVelocity()) > Variables.getInstance()
				.getDriveTrainHighGearThreshold()
				&& Math.abs(Encoders.getInstance().getRightVelocity()) > Variables.getInstance()
						.getDriveTrainHighGearThreshold()) {
			DriveTrain.getInstance().setHighGear(true);
		}
	}

	public boolean isFPS() {
		return _rightJoystick.getRawButton(1);
	}

	public double getPower() {
		return _power;
	}
}
