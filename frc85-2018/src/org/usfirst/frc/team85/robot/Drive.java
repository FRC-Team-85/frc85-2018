package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {

	protected static Drive instance = null;

	private MotorGroup _mgLeft = Globals.getInstance().getMotorGroupLeft();
	private MotorGroup _mgRight = Globals.getInstance().getMotorGroupRight();

	private Joystick _leftJoystick = Globals.getInstance().getLeftJoystick();
	private Joystick _rightJoystick = Globals.getInstance().getRightJoystick();
	
	private Gyro _gyro = Globals.getInstance().getGyro();
	
	private static PowerDistributionPanel _pdp = Globals.getInstance().getPowerDistributionPanel();
	
	private static Pneumatics _pneumatics = Globals.getInstance().getPneumatics();

	private double _speedRight = 0;
	private double _speedLeft = 0;

	private double _power = (double) SmartDashboard.getNumber("Power", 1);
	private double _amplitude = .35;
	
	private AutomatedMovement _automatedMovement = AutomatedMovement.none;
	private double _gyroInit = 0;
	private boolean _inTurn = false;
	
	private static final boolean LOW_GEAR = true;
	
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
		
		if(_automatedMovement == AutomatedMovement.none) {
			// Drivemode
			if (_rightJoystick.getRawButton(1)) {
				fpsDrive();
			} else {
				tankDrive();
			}
			
			// Transmission
			if(_leftJoystick.getRawButton(4)) { //lowgear
				manualTrans(LOW_GEAR);
			}
			else if(_leftJoystick.getRawButton(5)) {//highgear
				manualTrans(!LOW_GEAR);
			} else {
				autoTrans();
			}
			
			// Automated Drive
			if(_leftJoystick.getRawButton(6)) {
				_automatedMovement = AutomatedMovement.leftTurn;
				_gyroInit = _gyro.getAngle();
			}
		}
		else if(_automatedMovement == AutomatedMovement.leftTurn ||
				_automatedMovement == AutomatedMovement.rightTurn) {
			turnDegrees(90);
			_automatedMovement = AutomatedMovement.none;
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
	
	private void manualTrans(boolean lowGear) {
		if(lowGear) {
			_pneumatics.setLowGear(lowGear);
			SmartDashboard.putString("Transmission Gear", "Low Gear");
		}
		else {
			_pneumatics.setLowGear(!lowGear);
			SmartDashboard.putString("Transmission Gear", "High Gear");
		}
	}
	
	private void autoTrans() {
		double leftFrontCurrent = _pdp.getCurrent(Addresses.leftFrontTalon);
		double leftBackCurrent = _pdp.getCurrent(Addresses.leftBackTalon);
		
		double rightFrontCurrent = _pdp.getCurrent(Addresses.rightFrontTalon);
		double rightBackCurrent = _pdp.getCurrent(Addresses.rightBackTalon);
		
		if (Math.abs(leftFrontCurrent + leftBackCurrent + rightFrontCurrent + rightBackCurrent) > 150) { //Implement && current speed later
			_pneumatics.setLowGear(true);
		} else {
			_pneumatics.setLowGear(false); //Defaulted to high gear
		}
	}
	
	private boolean turnDegrees(double degrees) {
		if(_automatedMovement == AutomatedMovement.leftTurn &&_gyro.getAngle() < _gyroInit + degrees) {
			_mgLeft.setPower(1);
			_mgRight.setPower(-1);
			return false;
		} else if(_automatedMovement == AutomatedMovement.rightTurn &&_gyro.getAngle() > _gyroInit - degrees) {
			_mgLeft.setPower(-1);
			_mgRight.setPower(1);
			return false;
		} else {
			_mgLeft.setPower(0);
			_mgRight.setPower(0);
			_gyroInit = 0;
			return true;
		}
	}

}
