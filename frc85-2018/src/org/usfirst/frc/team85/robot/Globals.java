package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;

public class Globals {

	protected static Globals instance = null;

	private Joystick leftJoystick;
	private Joystick rightJoystick;
	private MotorGroup mgLeft; // Left Drive Train
	private MotorGroup mgRight; // Right Drive Train
	private ADXRS450_Gyro gyro;
	// private IMU imu;
	private RangeFinder rangeFinder;
	private TalonSRX leftIntakeWheel, rightIntakeWheel;
	private Solenoid leftIntakeSolenoid, rightIntakeSolenoid;
	private Compressor compressor;
	private Solenoid transmissionSolenoid;
	private Pneumatics pneumatics;
	private PowerDistributionPanel powerDistributionPanel;
	
	private Encoder leftDriveEncoder, rightDriveEncoder;
	private Encoders encoders; //encoders class

	private Globals() {

		leftJoystick = new Joystick(Addresses.leftDriveStick);
		rightJoystick = new Joystick(Addresses.rightDriveStick);

		mgLeft = new MotorGroup(new int[] { Addresses.leftBackTalon, Addresses.leftFrontTalon });
		mgRight = new MotorGroup(new int[] { Addresses.rightBackTalon, Addresses.rightFrontTalon });

		leftDriveEncoder = new Encoder(Addresses.leftChannelA, Addresses.leftChannelB);
		rightDriveEncoder = new Encoder(Addresses.rightChannelA, Addresses.rightChannelB);
		
		rangeFinder = RangeFinder.getInstance();

		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

		// imu = new IMU();
		
		powerDistributionPanel = new PowerDistributionPanel(Addresses.powerDistributionPanel);

		// leftIntakeWheel = new TalonSRX(Addresses.leftIntakeTalon);
		// rightIntakeWheel = new TalonSRX(Addresses.rightIntakeTalon);

		// leftIntakeSolenoid = new Solenoid(Addresses.leftIntakeSolenoid);
		// rightIntakeSolenoid = new Solenoid(Addresses.rightIntakeSolenoid);
	}

	public static Globals getInstance() {
		if (instance == null) {
			instance = new Globals();
		}
		return instance;
	}
	
	public PowerDistributionPanel getPowerDistributionPanel() {
		return powerDistributionPanel;
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
	
	// IMU getIMU() {
	// return imu;
	// }

	public RangeFinder getRangeFinder() {
		return rangeFinder;
	}

	public ADXRS450_Gyro getGyro() {
		return gyro;
	}

	public TalonSRX getLeftIntakeWheel() {
		return leftIntakeWheel;
	}

	public TalonSRX getRightIntakeWheel() {
		return rightIntakeWheel;
	}

	public Solenoid getLeftIntakeSolenoid() {
		return leftIntakeSolenoid;
	}

	public Solenoid getRightIntakeSolenoid() {
		return rightIntakeSolenoid;
	}
	
	public Compressor getCompressor() {
		return compressor;
	}
	
	public Pneumatics getPneumatics() {
		return pneumatics;
	}
	
	public Solenoid getTransmissionSolenoid() {
		return transmissionSolenoid;
	}
	
	public Encoder getLeftDriveEncoder() {
		return leftDriveEncoder;
	}
	
	public Encoder getRightDriveEncoder() {
		return rightDriveEncoder;
	}
	
	public Encoders getEncoders() {
		return encoders;
	}
}
