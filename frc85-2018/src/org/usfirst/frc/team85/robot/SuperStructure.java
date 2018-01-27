package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;

public class SuperStructure extends IterativeRobot {

	protected static SuperStructure structure = null;

	protected Controller controller;

	protected MotorGroup mgLeft; // Left Drive Train
	protected MotorGroup mgRight; // Right Drive Train
	protected ADXRS450_Gyro gyro;
	protected I2C ultra;

	protected Auto auto;

	public SuperStructure() {
		structure = this;
	}

	public static SuperStructure getInstance() {
		return structure;
	}

	public Controller getController() {
		return controller;
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
}
