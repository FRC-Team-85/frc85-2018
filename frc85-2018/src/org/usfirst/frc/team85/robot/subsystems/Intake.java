package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;
import org.usfirst.frc.team85.robot.Variables;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

	private static Intake _instance;

	private TalonSRX _leftMotor, _rightMotor;
	private Solenoid _protectionSolenoid, _applicationSolenoid;

	private Intake() {
		_leftMotor = new TalonSRX(Addresses.INTAKE_LEFT_MOTOR);
		_leftMotor.setNeutralMode(NeutralMode.Coast);
		_rightMotor = new TalonSRX(Addresses.INTAKE_RIGHT_MOTOR);
		_rightMotor.setNeutralMode(NeutralMode.Coast);

		_protectionSolenoid = new Solenoid(Addresses.INTAKE_PROTECTION_SOLENOID);
		_applicationSolenoid = new Solenoid(Addresses.INTAKE_APPLICATION_SOLENOID);
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
		_leftMotor.set(ControlMode.PercentOutput, -power);
		_rightMotor.set(ControlMode.PercentOutput, power);
	}

	public void setPower(double left, double right) {
		_leftMotor.set(ControlMode.PercentOutput, -left);
		_rightMotor.set(ControlMode.PercentOutput, right);
	}

	public void protect(boolean protect) {
		if (protect != isProtected()) {
			Variables.getInstance().addSolenoidFire();
		}
		_protectionSolenoid.set(!protect);
	}

	public boolean isProtected() {
		return !_protectionSolenoid.get();
	}

	public void apply(boolean apply) {
		if (apply != isApplied()) {
			Variables.getInstance().addSolenoidFire();
		}
		_applicationSolenoid.set(!apply);
	}

	public boolean isApplied() {
		return !_applicationSolenoid.get();
	}
}
