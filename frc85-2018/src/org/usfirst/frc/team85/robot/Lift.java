package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

public class Lift {
	
	private TalonSRX _leftMotor, _leftSlaveMotorOne, _leftSlaveMotorTwo, _rightMotor, _rightSlaveMotorOne, _rightSlaveMotorTwo;
	
	public void LiftMotors() { 
		_leftMotor = new TalonSRX(Addresses.leftLiftMasterTalon);
		_leftSlaveMotorOne = new TalonSRX(Addresses.leftLiftSlaveTalonOne);
		_leftSlaveMotorOne.follow(_leftMotor);
		_leftSlaveMotorTwo = new TalonSRX(Addresses.leftLiftMasterTalon);
		_leftSlaveMotorTwo.follow(_leftMotor);
		
		_rightMotor = new TalonSRX(Addresses.rightLiftMasterTalon);
		_rightSlaveMotorOne = new TalonSRX(Addresses.rightLiftSlaveTalonOne);
		_rightSlaveMotorOne.follow(_rightMotor);
		_rightSlaveMotorTwo = new TalonSRX(Addresses.rightLiftSlaveTalonTwo);
		_rightSlaveMotorTwo.follow(_rightMotor);
	}
	
	DigitalInput _toplimit,
				_bottomlimit;
	//Lift limits
	public void setLiftMotors(boolean up, double speed) {
		if (up && _toplimit.get()
			|| !up && _bottomlimit.get()) {
			_rightMotor.set(ControlMode.PercentOutput, 0);
			_leftMotor.set(ControlMode.PercentOutput,  0);

			_rightMotor.set(ControlMode.PercentOutput, speed);
			_leftMotor.set(ControlMode.PercentOutput, speed);
		} else {
			_rightMotor.set(ControlMode.PercentOutput, -1 * speed); 
			_leftMotor.set(ControlMode.PercentOutput, -1 * speed);
		}
	}
	
}
