package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

	private static Intake _instance;

	private TalonSRX _leftMotor, _rightMotor;

	private Intake() {
		_leftMotor = new TalonSRX(Addresses.INTAKE_LEFT_MOTOR);
		_rightMotor = new TalonSRX(Addresses.INTAKE_RIGHT_MOTOR);
	}

	public static Intake getInstance() {
		if (_instance == null) {
			_instance = new Intake();
		}

		return _instance;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void setPower(double power) {
		_leftMotor.set(ControlMode.PercentOutput, power);
		_rightMotor.set(ControlMode.PercentOutput, power);
	}

}
