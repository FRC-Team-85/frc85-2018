package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class SuperStructure extends IterativeRobot {

	protected static SuperStructure structure = null;

	protected Joystick _leftJoystick;
	protected Joystick _rightJoystick;
	protected MotorGroup mgLeft; // Left Drive Train
	protected MotorGroup mgRight; // Right Drive Train
	protected ADXRS450_Gyro gyro;
	protected I2C I2CReader;

	protected Auto auto;

	public SuperStructure() {
		structure = this;
	}

	public static SuperStructure getInstance() {
		return structure;
	}

	public MotorGroup getMotorGroupLeft() {
		return mgLeft;
	}

	public MotorGroup getMotorGroupRight() {
		return mgRight;
	}

	public ADXRS450_Gyro getGyro() {
		return gyro;
	}

	public Auto getAuto() {
		return auto;
	}

	public I2C getI2C() {
		return I2CReader;
	}
}
