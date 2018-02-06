package org.usfirst.frc.team85.robot;

public class Addresses {

	public final static int powerDistributionPanel = 0;
	
	// drive motors
	public final static int leftFrontTalon = 11;
	public final static int leftBackTalon = 12;
	public final static int rightFrontTalon = 14;
	public final static int rightBackTalon = 13;
	// drive pneumatics (transmission)
	public final static int transmissionSolenoid = 21;

	// lift motors
	public final static int rightLiftMasterTalon = 1;
	public final static int rightLiftSlaveTalonOne = 2;
	public final static int rightLiftSlaveTalonTwo = 3;
	public final static int leftLiftMasterTalon = 4;
	public final static int leftLiftSlaveTalonOne = 5;
	public final static int leftLiftSlaveTalonTwo = 6;

	// intake motors
	public final static int leftIntakeTalon = 7;
	public final static int rightIntakeTalon = 8;
	// intake pneumatics
	public final static int rightIntakeSolenoid = 15;
	public final static int leftIntakeSolenoid = 16;
	
	//ultrasonic rangefinders
	public final static int rangeFinder = 112;
	
	// joysticks
	public final static int leftDriveStick = 1;
	public final static int rightDriveStick = 0;

	public final static int tempTalon = 44; //Talon that the IMU is plugged into.
	
	// general pneumatics
	public final static int compressor = 0; //0 Is the default PCM node id.

}