package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.CancelCubeSearch;
import org.usfirst.frc.team85.robot.commands.CancelEjectCube;
import org.usfirst.frc.team85.robot.commands.CompressorActive;
import org.usfirst.frc.team85.robot.commands.CubeSearch;
import org.usfirst.frc.team85.robot.commands.EjectCube;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinExactDegrees;
import org.usfirst.frc.team85.robot.commands.drivetrain.ThreePointTurn;
import org.usfirst.frc.team85.robot.commands.drivetrain.ToggleTransmission;
import org.usfirst.frc.team85.robot.commands.drivetrain.VisionCubeSearch;
import org.usfirst.frc.team85.robot.commands.gripper.ToggleGripper;
import org.usfirst.frc.team85.robot.commands.intake.ActivateIntake;
import org.usfirst.frc.team85.robot.commands.intake.ToggleProtectIntake;
import org.usfirst.frc.team85.robot.commands.lift.LockLift;
import org.usfirst.frc.team85.robot.commands.lift.MoveLift;
import org.usfirst.frc.team85.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Lift;

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

	private Command _liftUp, _liftDown, _liftStop, _liftFastUp, _liftFastDown;
	private boolean _liftStopped = false;

	private double _speedLeft = 0, _speedRight = 0, _power = 1, _turningAmplitude = 0;

	private double _previousYaw = 0;

	private OI() {
		_leftJoystick = new Joystick(Addresses.LEFT_JOYSTICK);
		_rightJoystick = new Joystick(Addresses.RIGHT_JOYSTICK);
		_liftOperatorStation = new Joystick(Addresses.OPERATOR_STATION_LIFT);
		_miscOperatorStation = new Joystick(Addresses.OPERATOR_STATION_MISC);

		JoystickButton slideLeft = new JoystickButton(_rightJoystick, 7);
		JoystickButton slideRight = new JoystickButton(_rightJoystick, 8);
		slideLeft.whenPressed(new ThreePointTurn(1.0, -1, 2));
		slideRight.whenPressed(new ThreePointTurn(1.0, 1, 2));

		_liftUp = new MoveLift(Variables.getInstance().getLiftManualSpeed());
		_liftDown = new MoveLift(-Variables.getInstance().getLiftManualSpeed());
		_liftStop = new MoveLift(0);
		_liftFastUp = new MoveLift(Variables.getInstance().getLiftFastManualSpeed() - .5);
		_liftFastDown = new MoveLift(-Variables.getInstance().getLiftFastManualSpeed());

		JoystickButton manualTrans = new JoystickButton(_leftJoystick, 2);
		manualTrans.whenPressed(new ToggleTransmission());

		JoystickButton turnLeft = new JoystickButton(_leftJoystick, 4); // button?
		JoystickButton turnRight = new JoystickButton(_leftJoystick, 5); // button?
		JoystickButton turnAround = new JoystickButton(_leftJoystick, 3); // button?
		turnLeft.whenPressed(new SpinExactDegrees(90, 1));
		turnRight.whenPressed(new SpinExactDegrees(-90, 1));
		turnAround.whenPressed(new SpinExactDegrees(180, 1.5));

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
		JoystickButton visionSearchButton = new JoystickButton(_miscOperatorStation, Addresses.OS_MISC_VISION_SEARCH);

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

		visionSearchButton.whenPressed(new VisionCubeSearch());
		visionSearchButton.whenReleased(new CancelCubeSearch());
	}

	public void periodic() {
		double liftJoystickAxis1 = _liftOperatorStation.getRawAxis(1);
		double liftJoystickAxis2 = _liftOperatorStation.getRawAxis(0);

		if (liftJoystickAxis1 == -1) {
			_liftUp.start();
			_liftStopped = false;
		} else if (liftJoystickAxis1 == 1) {
			_liftDown.start();
			_liftStopped = false;
		} else if (liftJoystickAxis2 == 1) {
			_liftFastUp.start();
			_liftStopped = false;
		} else if (liftJoystickAxis2 == -1) {
			_liftFastDown.start();
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

		if ((rightStick >= .07) || (rightStick <= -.1)) {
			_speedRight = (Math.pow(rightStick, _power)
					+ Variables.getInstance().getUsefulDriveTrainPower() * (Math.abs(rightStick) / rightStick))
					* tractionControl();
		} else if ((rightStick < .07) && (rightStick > -.1)) {
			_speedRight = 0;
		}

		if ((leftStick >= .07) || (leftStick <= -.1)) {
			_speedLeft = (Math.pow(leftStick, _power)
					+ Variables.getInstance().getUsefulDriveTrainPower() * (Math.abs(leftStick) / leftStick))
					* tractionControl();
		} else if ((leftStick < .07) && (leftStick > -.1)) {
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
					_speedRight = (_rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * _turningAmplitude)
							* tractionControl();
				} else {
					_speedRight = (_rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * _turningAmplitude)
							* tractionControl();
				}

			} else if (_leftJoystick.getRawAxis(0) < -.1) {
				if (_rightJoystick.getRawAxis(1) > 0) {
					_speedLeft = (_rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * _turningAmplitude)
							* tractionControl();
				} else {
					_speedLeft = (_rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * _turningAmplitude)
							* tractionControl();
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

	/**
	 * Trys to not tip the robot over
	 */
	public double tractionControl() {
		double leftVelocity = Encoders.getInstance().getLeftVelocity();
		double rightVelocity = Encoders.getInstance().getRightVelocity();
		double yaw = IMU.getInstance().getYaw();
		double pitch = IMU.getInstance().getPitch();
		double roll = IMU.getInstance().getRoll();

		double lift = Lift.getInstance().getPosition();
		double turnRate = Math.abs(yaw - _previousYaw);

		_previousYaw = yaw;

		if (Math.abs(roll) > 12) { // If tilting left or right
			if (lift > 13000) { // Move lift down
				Lift.getInstance().setDesiredHeight(10000);
				return 1.0;
			} else if ((Math.abs(leftVelocity) > 10 && Math.abs(rightVelocity) > 10) || (turnRate > 10)) { // Slow robot
																											// down
				/*
				 * Returns multiplier (For example, 0.90) which the speed (in tank and fps
				 * drive) is multiplied by it to slow robot down (by 10%)
				 */
				return Variables.getInstance().getTractionControlMultiplier();
			} else {
				return 1.0;
			}
		}

		if (Math.abs(pitch) > 20) { // If tilting forwards or backwards
			if (lift > 13000) { // Move lift down
				Lift.getInstance().setDesiredHeight(10000);
				return 1.0;
			} else if (Math.abs(leftVelocity) > 10 && Math.abs(rightVelocity) > 10) {
				// Slow robot down
				return Variables.getInstance().getTractionControlMultiplier();
			} else {
				return 1.0;
			}
		}
		return 1.0;
	}

	public boolean isFPS() {
		return _rightJoystick.getRawButton(1);
	}

	public double getPower() {
		return _power;
	}

	public double getLeftJoystick() {
		if (_leftJoystick != null) {
			return _leftJoystick.getRawAxis(1);
		} else {
			return 0;
		}
	}

	public double getRightJoystick() {
		if (_rightJoystick != null) {
			return _rightJoystick.getRawAxis(1);
		} else {
			return 0;
		}
	}
}
