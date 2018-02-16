package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Lift extends PIDSubsystem {

	private static Lift _instance = null;

	public static final double kP = .2, kI = 0.0, kD = 0.0;
	private TalonSRX _leftOne, _leftTwo, _leftThree, _leftFour, _rightOne, _rightTwo, _rightThree, _rightFour;

	private Lift() {
		super(kP, kI, kD);
		_leftOne = new TalonSRX(Addresses.LIFT_LEFT_ONE);
		_leftTwo = new TalonSRX(Addresses.LIFT_LEFT_TWO);
		_leftTwo.follow(_leftOne);
		_leftThree = new TalonSRX(Addresses.LIFT_LEFT_THREE);
		_leftThree.follow(_leftOne);
		_leftFour = new TalonSRX(Addresses.LIFT_LEFT_FOUR);
		_leftFour.follow(_leftOne);

		_rightOne = new TalonSRX(Addresses.LIFT_LEFT_ONE);
		_rightTwo = new TalonSRX(Addresses.LIFT_LEFT_TWO);
		_rightTwo.follow(_rightOne);
		_rightThree = new TalonSRX(Addresses.LIFT_LEFT_THREE);
		_rightThree.follow(_rightOne);
		_rightFour = new TalonSRX(Addresses.LIFT_LEFT_FOUR);
		_rightFour.follow(_rightOne);
	}

	public static Lift getInstance() {
		if (_instance == null) {
			_instance = new Lift();
		}
		return _instance;
	}

	@Override
	protected void initDefaultCommand() {

	}

	@Override
	protected double returnPIDInput() {
		return 0; // Get Lift Encoder Values
	}

	@Override
	protected void usePIDOutput(double output) {
		// set _leftMotor and _rightMotor
	}
}
