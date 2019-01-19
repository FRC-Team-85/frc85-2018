package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;
import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.commands.drivetrain.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private static DriveTrain _instance = null;
	private TalonSRX _leftFrontMotor, _leftBackMotor, _rightFrontMotor, _rightBackMotor;
	private Solenoid _transmissionSolenoid;

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

		_transmissionSolenoid = new Solenoid(0, Addresses.TRANSMISSION_SOLENOID);
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
		_rightFrontMotor.set(ControlMode.PercentOutput, -right);
	}

	public void drive(double[] speed) {
		_leftFrontMotor.set(ControlMode.PercentOutput, speed[0]);
		_rightFrontMotor.set(ControlMode.PercentOutput, -speed[1]);
	}

	public void setHighGear(boolean highGear) {
		if (highGear != getTransmissionHighGear()) {
			Variables.getInstance().addSolenoidFire();
		}

		_transmissionSolenoid.set(highGear);
	}

	public boolean getTransmissionHighGear() {
		return _transmissionSolenoid.get();
	}

	public double getLeftFrontCurrent() {
		return _leftFrontMotor.getOutputCurrent();
	}

	public double getLeftBackCurrent() {
		return _leftBackMotor.getOutputCurrent();
	}

	public double getRightFrontCurrent() {
		return _rightFrontMotor.getOutputCurrent();
	}

	public double getRightBackCurrent() {
		return _rightBackMotor.getOutputCurrent();
	}

	public double getTotalCurrent() {
		return getLeftFrontCurrent() + getLeftBackCurrent() + getRightFrontCurrent() + getRightBackCurrent();
	}

	public double getLeftFrontPercent() {
		return _leftFrontMotor.getMotorOutputPercent();
	}

	public double getLeftBackPercent() {
		return _leftBackMotor.getMotorOutputPercent();
	}

	public double getRightFrontPercent() {
		return _rightFrontMotor.getMotorOutputPercent();
	}

	public double getRightBackPercent() {
		return _rightBackMotor.getMotorOutputPercent();
	}

}
