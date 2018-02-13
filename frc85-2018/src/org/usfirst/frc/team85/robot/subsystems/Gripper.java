package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gripper extends Subsystem {

	private static Gripper _instance;

	private Solenoid _leftSolenoid, _rightSolenoid;

	private Gripper() {
		_leftSolenoid = new Solenoid(Addresses.GRIPPER_LEFT_SOLENOID);
		_rightSolenoid = new Solenoid(Addresses.GRIPPER_RIGHT_SOLENOID);
	}

	public static Gripper getInstance() {
		if (_instance == null) {
			_instance = new Gripper();
		}
		return _instance;
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void open() {
		_leftSolenoid.set(true);
		_rightSolenoid.set(true);
	}

	public void close() {
		_leftSolenoid.set(false);
		_rightSolenoid.set(false);
	}

	public boolean isOpen() {
		return _leftSolenoid.get();
	}
}
