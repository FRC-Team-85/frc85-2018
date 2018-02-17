package org.usfirst.frc.team85.robot;

public class Addresses {

	// PDP
	public static final int POWER_DISTRIBUTION_PANEL = 0;

	// DriveTrain
	public static final int DRIVETRAIN_LEFT_FRONT_MOTOR = 11;
	public static final int DRIVETRAIN_LEFT_BACK_MOTOR = 12;
	public static final int DRIVETRAIN_RIGHT_FRONT_MOTOR = 14;
	public static final int DRIVETRAIN_RIGHT_BACK_MOTOR = 13;
	public static final int TRANSMISSION_SOLENOID = 1;

	// Lift
	public static final int LIFT_LEFT_ONE = 5;
	public static final int LIFT_LEFT_TWO = 6;
	public static final int LIFT_LEFT_THREE = 7;
	public static final int LIFT_LEFT_FOUR = 8;
	public static final int LIFT_LEFT_LOCK = 0;
	public static final int LIFT_RIGHT_LOCK = 0;

	// Joystick
	public static final int LEFT_JOYSTICK = 1;
	public static final int RIGHT_JOYSTICK = 0;
	public static final int OPERATOR_STATION_LIFT = 2;
	public static final int OPERATOR_STATION_MISC = 3;

	// Gripper
	public static final int GRIPPER_SOLENOID = 0;

	// Intake
	public static final int INTAKE_LEFT_MOTOR = 0;
	public static final int INTAKE_RIGHT_MOTOR = 0;
	public static final int INTAKE_LEFT_PROTECTION_SOLENOID = 0;
	public static final int INTAKE_RIGHT_PROTECTION_SOLENOID = 0;
	public static final int INTAKE_LEFT_APPLICATION_SOLENOID = 0;
	public static final int INTAKE_RIGHT_APPLICATION_SOLENOID = 0;

	// Sensors
	public static final int RANGEFINDER = 112;
	public static final int IMU_TALON = 13;

	// cameras
	public static final int FORWARD_CAMERA = 0;
	public static final int REVERSE_CAMERA = 1;

	// drive encoders (DIO ports)
	public final static int ENCODERS_LEFT_A = 2;
	public final static int ENCODERS_LEFT_B = 3;
	public final static int ENCODERS_RIGHT_A = 0;
	public final static int ENCODERS_RIGHT_B = 1;

	// Lift Operator Station
	public final static int OS_LIFT_SWITCH = 1;
	public final static int OS_LIFT_GROUND = 2;
	public final static int OS_LIFT_PLATFORM = 3;
	public final static int OS_LIFT_LOW_SCALE = 4;
	public final static int OS_LIFT_MEDIUM_SCALE = 5;
	public final static int OS_LIFT_HIGH_SCALE = 6;
	public final static int OS_LIFT_DOUBLE_SCALE = 7;
	public final static int OS_LIFT_CLIMB = 8;

	// Misc Operator Station
	public final static int OS_MISC_TOGGLE_GRIPPER = 1;
	public final static int OS_MISC_UNKNOWN = 2;
	public final static int OS_MISC_INTAKE_FORWARD = 3;
	public final static int OS_MISC_INTAKE_REVERSE = 4;
	public final static int OS_MISC_COMPRESSOR_ON = 5;
	public final static int OS_MISC_COMPRESSOR_OFF = 6;
	public final static int OS_MISC_LEFT_CHICKEN_WING = 7;
	public final static int OS_MISC_RIGHT_CHICKEN_WING = 8;
}