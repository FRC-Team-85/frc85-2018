package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Lift {
	
	private static Intake _intake = Globals.getInstance().getIntake();
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
	
	private int height = 0;
	public Joystick _board = new Joystick(20);
	
	//encoder for lift
	private Encoder _liftEncoder = new Encoder(1, 1);
	
	//_leftMotor.configSelectedFeedbackSource(FeedbackDevice.QuadEncoder, 0, 0));
	
	public void liftMoving() {
		if (_liftEncoder.get() > height) {
				_leftMotor.set(ControlMode.PercentOutput, -.5);
				_rightMotor.set(ControlMode.PercentOutput, -.5);
		} else if (_liftEncoder.get() < height) {
				_leftMotor.set(ControlMode.PercentOutput, .5);
				_rightMotor.set(ControlMode.PercentOutput, .5);
		}
	}
	
	//manually adjusting the lift
	public void operator() {
		if (_board.getRawAxis(1) > .2) {
			_leftMotor.set(ControlMode.PercentOutput, -.5);
			_rightMotor.set(ControlMode.PercentOutput, -.5);
			height = _liftEncoder.get();
		} else if (_board.getRawAxis(1) < -.2) {
			_leftMotor.set(ControlMode.PercentOutput, .5);
			_rightMotor.set(ControlMode.PercentOutput, .5);
			height = _liftEncoder.get();
		} else {
			_leftMotor.set(ControlMode.PercentOutput, 0);
			_rightMotor.set(ControlMode.PercentOutput, 0);
		}
	}
	
	//initial state
	public void groundState() { //button 1
		height = 0;
	}
	
	//20 inches
	public void switchState() { //button 2
		height = 81920;
		_intake.setIntakePosition(false);
	}
	
	//4.25 ft
	public void lowState() { //button 3
		height = 204800;
		_intake.setIntakePosition(false);
	}
	
	//6.25 ft
	public void midState() { //button 4
		height = 327680;
		_intake.setIntakePosition(false);
	}
	
	//8 ft
	public void topHeight() { //button 5
		height = 409600;
		_intake.setIntakePosition(false);
	}
	
}
