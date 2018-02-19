package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem {

	private static Lift _instance = null;

	private double kP = 0.1, kI = 0.0, kD = 0.2;

	private TalonSRX _leftOne, _leftTwo, _rightOne, _rightTwo;
	private Solenoid _lock;

	private Lift() {
		_rightOne = new TalonSRX(Addresses.LIFT_RIGHT_ONE);
		_rightOne.setNeutralMode(NeutralMode.Brake);
		_rightOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		_rightOne.setSelectedSensorPosition(0, 0, 0);
		_rightOne.selectProfileSlot(0, 0);
		_rightOne.config_kP(0, kP, 0);
		_rightOne.config_kI(0, kI, 0);
		_rightOne.config_kD(0, kD, 0);
		_rightOne.config_kF(0, 0, 0);

		_rightTwo = new TalonSRX(Addresses.LIFT_RIGHT_TWO);
		_rightTwo.setNeutralMode(NeutralMode.Brake);
		_rightTwo.follow(_rightOne);

		_leftOne = new TalonSRX(Addresses.LIFT_LEFT_ONE);
		_leftOne.setNeutralMode(NeutralMode.Brake);
		_leftOne.follow(_rightOne);

		_leftTwo = new TalonSRX(Addresses.LIFT_LEFT_TWO);
		_leftTwo.setNeutralMode(NeutralMode.Brake);
		_leftTwo.follow(_rightOne);

		_lock = new Solenoid(Addresses.LIFT_LOCK);

		SmartDashboard.putNumber("Lift/kP", kP);
		SmartDashboard.putNumber("Lift/kI", kI);
		SmartDashboard.putNumber("Lift/kD", kD);
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

	public void setHeight(double height) {
		kP = SmartDashboard.getNumber("Lift/kP", kP);
		kI = SmartDashboard.getNumber("Lift/kI", kI);
		kD = SmartDashboard.getNumber("Lift/kD", kD);
		_rightOne.set(ControlMode.Position, -height);
	}

	public void setPower(double power) {
		_rightOne.set(ControlMode.PercentOutput, power);
	}

	public double getPosition() {
		SmartDashboard.putNumber("Sel Sensor Position", -_rightOne.getSelectedSensorPosition(0));
		return -_rightOne.getSelectedSensorPosition(0);
	}

	public void lock(boolean lock) {
		_lock.set(lock);
	}
}
