package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

	private static Intake _instance;

	private TalonSRX _leftMotor, _rightMotor;
	private Solenoid _leftProtectionSolenoid, _leftApplicationSolenoid, _rightProtectionSolenoid,
			_rightApplicationSolenoid;

	private Intake() {
		_leftMotor = new TalonSRX(Addresses.INTAKE_LEFT_MOTOR);
		_rightMotor = new TalonSRX(Addresses.INTAKE_RIGHT_MOTOR);

		_leftProtectionSolenoid = new Solenoid(Addresses.INTAKE_LEFT_PROTECTION_SOLENOID);
		_rightProtectionSolenoid = new Solenoid(Addresses.INTAKE_RIGHT_PROTECTION_SOLENOID);
		_leftApplicationSolenoid = new Solenoid(Addresses.INTAKE_LEFT_APPLICATION_SOLENOID);
		_rightApplicationSolenoid = new Solenoid(Addresses.INTAKE_RIGHT_APPLICATION_SOLENOID);
	}

	public static Intake getInstance() {
		if (_instance == null) {
			_instance = new Intake();
		}

		return _instance;
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void setPower(double power) {
		_leftMotor.set(ControlMode.PercentOutput, power);
		_rightMotor.set(ControlMode.PercentOutput, power);
	}

	public void protect(boolean protect) {
		_leftProtectionSolenoid.set(protect);
		_rightProtectionSolenoid.set(protect);
	}

	public void apply(boolean apply) {
		_leftApplicationSolenoid.set(apply);
		_rightApplicationSolenoid.set(apply);
	}

}
