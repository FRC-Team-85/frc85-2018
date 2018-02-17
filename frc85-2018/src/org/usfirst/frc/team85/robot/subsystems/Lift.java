package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Lift extends PIDSubsystem {

	private static Lift _instance = null;

	public static final double kP = .2, kI = 0.0, kD = 0.0;
	private TalonSRX _one, _two, _three, _four;
	private Solenoid _lock;

	private Lift() {
		super(kP, kI, kD);
		_one = new TalonSRX(Addresses.LIFT_LEFT_ONE);
		_two = new TalonSRX(Addresses.LIFT_LEFT_TWO);
		_two.follow(_one);
		_three = new TalonSRX(Addresses.LIFT_RIGHT_ONE);
		_three.follow(_one);
		_four = new TalonSRX(Addresses.LIFT_RIGHT_TWO);
		_four.follow(_one);

		_lock = new Solenoid(Addresses.LIFT_LOCK);
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

	@Override
	protected double returnPIDInput() {
		return 0; // Get Lift Encoder Values
	}

	@Override
	protected void usePIDOutput(double output) {
		// set _leftMotor and _rightMotor
	}

	public void setHeight(double height) {
		setSetpoint(height); // Needs conversion from encoder counts to true height
		// possibly make constants for each height and increment for movement
	}

	public void lock(boolean lock) {
		_lock.set(lock);
	}
}
