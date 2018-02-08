package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {

	private static Lift _instance = null;
	private TalonSRX _leftOne, _leftTwo, _leftThree, _leftFour, _rightOne, _rightTwo, _rightThree, _rightFour;

	private Lift() {
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
}
