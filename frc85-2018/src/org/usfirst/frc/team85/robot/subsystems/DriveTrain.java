package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;
import org.usfirst.frc.team85.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private static DriveTrain _instance = null;
	private TalonSRX _leftFrontMotor, _leftBackMotor, _rightFrontMotor, _rightBackMotor;

	private DriveTrain() {
		_leftFrontMotor = new TalonSRX(Addresses.DRIVETRAIN_LEFT_FRONT_MOTOR);
		_leftFrontMotor.setNeutralMode(NeutralMode.Brake);

		_leftBackMotor = new TalonSRX(Addresses.DRIVETRAIN_LEFT_BACK_MOTOR);
		_leftBackMotor.follow(_leftFrontMotor);
		_leftBackMotor.setNeutralMode(NeutralMode.Brake);

		_rightFrontMotor = new TalonSRX(Addresses.DRIVETRAIN_RIGHT_FRONT_MOTOR);
		_rightFrontMotor.setNeutralMode(NeutralMode.Brake);

		_rightBackMotor = new TalonSRX(Addresses.DRIVETRAIN_RIGHT_BACK_MOTOR);
		_rightBackMotor.follow(_rightFrontMotor);
		_rightBackMotor.setNeutralMode(NeutralMode.Brake);
	}

	public static DriveTrain getInstance() {
		if (_instance == null) {
			_instance = new DriveTrain();
		}
		return _instance;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void drive(double left, double right) {
		_leftFrontMotor.set(ControlMode.PercentOutput, left);
		_rightFrontMotor.set(ControlMode.PercentOutput, right);
	}

	public void drive(double[] speed) {
		_leftFrontMotor.set(ControlMode.PercentOutput, speed[0]);
		_rightFrontMotor.set(ControlMode.PercentOutput, speed[1]);
	}

}
