package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
	public void setLiftMotors(boolean up, double speed) {			// there should be a better way to do this, currently escaping me
		if (up && _toplimit.get()									// first, check if it can even move
			|| !up && _bottomlimit.get()) {
			_rightMotor.set(ControlMode.PercentOutput, 0);
			_leftMotor.set(ControlMode.PercentOutput,  0);
		} else if (up) {											// then, see if it's going up
			_rightMotor.set(ControlMode.PercentOutput, speed);
			_leftMotor.set(ControlMode.PercentOutput, speed);
		} else if (!up) {											// then, see if it's going down
			_rightMotor.set(ControlMode.PercentOutput, -1 * speed); 
			_leftMotor.set(ControlMode.PercentOutput, -1 * speed);
		} else {													// lastly, stop if neither of the others; otherwise, will continue going in the last set direction
			_rightMotor.set(ControlMode.PercentOutput, 0);
			_leftMotor.set(ControlMode.PercentOutput,  0);
		}
	}
	
	
	//encoder for lift
	private Encoder _liftEncoder = new Encoder(1, 1); // this'll be relevant someday, it WILL NOT currently work
	
	//_leftMotor.configSelectedFeedbackSource(FeedbackDevice.QuadEncoder, 0, 0));
	
	private double liftTolerance = 1; // testing this tolerance later
	private double height;
	
	public void setLiftHeight(double targetHeight) { // moves the lift to a certain height, adjust tolerance as necessary
		if (_liftEncoder.get() > (targetHeight + liftTolerance)) {
				setLiftMotors(false, .5);
		} else if (_liftEncoder.get() < (targetHeight - liftTolerance)) {
				setLiftMotors(true, .5);
		}
		height = _liftEncoder.get();
	}
	
	//manually adjusting the lift
	/*
	public void operateLift(double speed) {	// why are we doing the check for the stick inside here?
		if (_opBoard.getLiftStick() > .2) {
			setLiftMotors(true, .5);
		} else if (_opBoard.getLiftStick() < -.2) {
			setLiftMotors(false, -.5);
		} else {
			setLiftMotors(true, 0);
		}
		height = _liftEncoder.get();
	}
	// no idea why i'm keeping this*/
	
	
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
