package org.usfirst.frc.team85.robot;

public class Addresses {

	// DriveTrain
	public static final int DRIVETRAIN_LEFT_FRONT_MOTOR = 11;
	public static final int DRIVETRAIN_LEFT_BACK_MOTOR = 12;
	public static final int DRIVETRAIN_RIGHT_FRONT_MOTOR = 14;
	public static final int DRIVETRAIN_RIGHT_BACK_MOTOR = 13;
	public static final int TRANSMISSION_SOLENOID = 2;

	// Lift
	public static final int LIFT_RIGHT_ONE = 5;
	public static final int LIFT_RIGHT_TWO = 6;
	public static final int LIFT_LEFT_ONE = 7;
	public static final int LIFT_LEFT_TWO = 8;
	public static final int LIFT_LOCK = 3;

	// Joystick
	public static final int LEFT_JOYSTICK = 1;
	public static final int RIGHT_JOYSTICK = 0;
	public static final int OPERATOR_STATION_LIFT = 2;
	public static final int OPERATOR_STATION_MISC = 3;

	// Gripper
	public static final int GRIPPER_SOLENOID = 0;

	// Intake
	public static final int INTAKE_LEFT_MOTOR = 9;
	public static final int INTAKE_RIGHT_MOTOR = 10;
	public static final int INTAKE_PROTECTION_SOLENOID = 4;
	public static final int INTAKE_APPLICATION_SOLENOID = 1;

	// Sensors
	public static final int RANGEFINDER_FRONT = 53;
	public static final int RANGEFINDER_LEFT = 51;
	public static final int RANGEFINDER_RIGHT = 50;
	public static final int RANGEFINDER_BACK = 52;

	public static final int IMU_TALON = 6;
	public static final int LIFT_LOWER_LIMIT_SWITCH = 8;
	public static final int LIFT_UPPER_LIMIT_SWITCH = 9;
	public static final int INTAKE_LEFT_LIMIT = 5;
	public static final int INTAKE_RIGHT_LIMIT = 4;

	// Indicators
	public static final int INDICATORS_LEFT_GREEN = 0;
	public static final int INDICATORS_LEFT_RED = 1;
	public static final int INDICATORS_RIGHT_GREEN = 2;
	public static final int INDICATORS_RIGHT_RED = 3;

	// drive encoders (DIO ports)
	public final static int ENCODERS_LEFT_A = 2;
	public final static int ENCODERS_LEFT_B = 3;
	public final static int ENCODERS_RIGHT_A = 0;
	public final static int ENCODERS_RIGHT_B = 1;

	// Lift Operator Station
	public final static int OS_LIFT_PLATFORM_SWITCH = 1;
	public final static int OS_LIFT_GROUND = 2;
	public final static int OS_LIFT_LOCK = 3;
	public final static int OS_LIFT_LOW_SCALE = 4;
	public final static int OS_LIFT_MEDIUM_SCALE = 5;
	public final static int OS_LIFT_HIGH_SCALE = 6;
	public final static int OS_LIFT_DOUBLE_SCALE = 7;

	// Misc Operator Station
	public final static int OS_MISC_TOGGLE_GRIPPER = 1;
	public final static int OS_MISC_INTAKE_PROTECT = 2;
	public final static int OS_MISC_INTAKE_FORWARD = 3;
	public final static int OS_MISC_INTAKE_REVERSE = 4;
	public final static int OS_MISC_COMPRESSOR_ON = 5;
	public final static int OS_MISC_COMPRESSOR_OFF = 6;
	public final static int OS_MISC_CUBE_SEARCH = 9;
	public final static int OS_MISC_EXCHANGE_BUTTON = 10;
	public final static int OS_MISC_LIFT_CLIMB = 11;
	public final static int OS_MISC_VISION_SEARCH = 8;
}