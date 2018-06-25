package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.Addresses;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitches {

	private static LimitSwitches _instance;
	private DigitalInput _lowerLiftLimit, _upperLiftlimit, _leftIntakeLimit, _rightIntakeLimit;

	private LimitSwitches() {
		_lowerLiftLimit = new DigitalInput(Addresses.LIFT_LOWER_LIMIT_SWITCH);
		_upperLiftlimit = new DigitalInput(Addresses.LIFT_UPPER_LIMIT_SWITCH);
		_leftIntakeLimit = new DigitalInput(Addresses.INTAKE_LEFT_LIMIT);
		_rightIntakeLimit = new DigitalInput(Addresses.INTAKE_RIGHT_LIMIT);
	}

	public static LimitSwitches getInstance() {
		if (_instance == null) {
			_instance = new LimitSwitches();
		}
		return _instance;
	}

	public boolean getLowerLiftLimit() {
		return _lowerLiftLimit.get();
	}

	public boolean getUpperLiftLimit() {
		return _upperLiftlimit.get();
	}

	public boolean getLeftIntakeLimit() {
		return !_leftIntakeLimit.get();
	}

	public boolean getRightIntakeLimit() {
		return !_rightIntakeLimit.get();
	}
}
