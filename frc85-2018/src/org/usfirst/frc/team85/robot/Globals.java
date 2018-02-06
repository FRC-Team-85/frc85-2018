package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;

public class Globals {

	protected static Globals instance = null;

	protected Joystick leftJoystick;
	protected Joystick rightJoystick;
	protected MotorGroup mgLeft; // Left Drive Train
	protected MotorGroup mgRight; // Right Drive Train
	protected ADXRS450_Gyro gyro;
	protected RangeFinder rangeFinder;
	protected TalonSRX leftIntakeWheel, rightIntakeWheel;
	protected Solenoid leftIntakeSolenoid, rightIntakeSolenoid;
	protected Intake intake;
	protected Lift lift;
	
	private Globals() {
	}
	
	public void init() {
		leftJoystick = new Joystick(Addresses.leftDriveStick);
		rightJoystick = new Joystick(Addresses.rightDriveStick);

		mgLeft = new MotorGroup(new int[] { Addresses.leftBackTalon, Addresses.leftFrontTalon });
		mgRight = new MotorGroup(new int[] { Addresses.rightBackTalon, Addresses.rightFrontTalon });

		try {
			gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
			gyro.setPIDSourceType(PIDSourceType.kDisplacement);
			gyro.calibrate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static Globals getInstance() {
		if(instance.equals(null)) {
			instance = new Globals();
		}
		return instance;
	}
	
	public Joystick getLeftJoystick() {
		return leftJoystick;
	}
	
	public Joystick getRightJoystick() {
		return rightJoystick;
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

	public RangeFinder getRangeFinder() {
		return rangeFinder;
	}
	
	public Intake getIntake() {
		return intake;
	}
	
	public Lift getLift() {
		return lift;
	}
	
}
