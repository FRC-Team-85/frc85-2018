package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive {
	private TalonSRX leftFront, rightFront, leftBack, rightBack;
	private TalonSRX[] left = { leftFront, leftBack };
	private TalonSRX[] right = { rightFront, rightBack };

	private double _leftSpeed;
	private double _rightSpeed;

	private static Drive instance = null;

	/**
	 * Singleton for Drive class
	 * 
	 * @return The single instance of Drive class
	 */
	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive(Addresses.leftFrontTalon, Addresses.leftBackTalon, Addresses.rightFrontTalon,
					Addresses.rightBackTalon);
		}
		return instance;
	}

	/**
	 * Constructor for Drive class
	 * 
	 * @param id1
	 *            ID of leftFront Talon
	 * @param id2
	 *            ID of leftBack Talon
	 * @param id3
	 *            ID of rightFront Talon
	 * @param id4
	 *            ID of rightBack Talon
	 */
	public Drive(int id1, int id2, int id3, int id4) {
		leftFront = new TalonSRX(id1);
		leftBack = new TalonSRX(id2);
		rightFront = new TalonSRX(id3);
		rightBack = new TalonSRX(id4);
	}

	/**
	 * Sets all motors to their appropriate speeds
	 */
	public void setMotors(double _leftV, double _rightV) {
		leftFront.set(ControlMode.PercentOutput, _leftV);
		leftBack.set(ControlMode.PercentOutput, _leftV);
		rightFront.set(ControlMode.PercentOutput, _rightV);
		rightBack.set(ControlMode.PercentOutput, _rightV);

	}

	/**
	 * Mutator method for _leftSpeed
	 * 
	 * @param speed
	 *            Desired speed for left motors
	 */
	public void setLeftSpeed(double speed) {
		_leftSpeed = speed;
	}

	/**
	 * Mutator method for _rightSpeed
	 * 
	 * @param speed
	 *            Desired speed for right motors
	 */
	public void setRightSpeed(double speed) {
		_rightSpeed = speed;
	}

	/**
	 * Combined mutator method for _leftSpeed and _rightSpeed
	 * 
	 * @param speed
	 *            Desired speed for right motors
	 */
	public void setSpeed(double speed) {
		_leftSpeed = speed;
		_rightSpeed = speed;
	}

	/**
	 * Accessor method for _leftSpeed
	 * 
	 * @return Desired speed for left motors
	 */
	public double getLeftSpeed() {
		return _leftSpeed;
	}

	/**
	 * Accessor method for _rightSpeed
	 * 
	 * @return Desired speed for right motors
	 */
	public double getRightSpeed() {
		return _rightSpeed;
	}
}
