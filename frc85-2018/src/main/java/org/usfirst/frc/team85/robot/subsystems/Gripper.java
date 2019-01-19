package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;
import org.usfirst.frc.team85.robot.Variables;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gripper extends Subsystem {

	private static Gripper _instance;

	private Solenoid _solenoid;

	private Gripper() {
		_solenoid = new Solenoid(Addresses.GRIPPER_SOLENOID);
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
		if (!isOpen()) {
			Variables.getInstance().addSolenoidFire();
		}
		_solenoid.set(true);
	}

	public void close() {
		if (isOpen()) {
			Variables.getInstance().addSolenoidFire();
		}
		_solenoid.set(false);
	}

	public boolean isOpen() {
		return _solenoid.get();
	}
}
