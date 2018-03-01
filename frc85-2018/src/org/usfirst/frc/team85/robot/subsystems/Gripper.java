package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;
import org.usfirst.frc.team85.robot.Diagnostics;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gripper extends Subsystem {
	
	private Diagnostics _diagnostics;

	private static Gripper _instance;

	private Solenoid _solenoid;

	private Gripper() {
		_diagnostics = new Diagnostics();
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
		_solenoid.set(true);
		_diagnostics.totalSolenoid++;
	}

	public void close() {
		_solenoid.set(false);
		_diagnostics.totalSolenoid++;
	}

	public boolean isOpen() {
		return _solenoid.get();
	}
}
