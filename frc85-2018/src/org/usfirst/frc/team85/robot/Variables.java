package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.sensors.LimitSwitches;
import org.usfirst.frc.team85.robot.sensors.RangeFinder;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Variables {

	private static Variables _instance;

	private static final double kP_SPIN = 0.075, kI_SPIN = 0.00001, kD_SPIN = 0.2, tolerance_SPIN = 8;

	public static final double g = 32.2;
	public static final double mu = .75;
	public static final double wheelSpan = 1.583;
	public static final double maxSpeed = 16;

	public static final double LIFT_GROUND = 0;
	public static final double LIFT_PLATFORM = 4000;
	public static final double LIFT_SWITCH = 10500;
	public static final double LIFT_LOCK = 9500;
	public static final double LIFT_SCALE_LOW = 21500;
	public static final double LIFT_SCALE = 30000;
	public static final double LIFT_CLIMB = 34500;
	public static final double LIFT_SCALE_HIGH = 37700;
	public static final double LIFT_SCALE_HIGH_DOUBLE = 39600;

	public static final double DefaultLiftTolerance = 400;
	public static final double DefaultLiftDownwardDecelMultiple = 12;
	public static final double DefaultLiftUpwardDecelMultiple = 6;

	private static final double DefaultDriveTrainCurrentThreshold = 155;
	private static final double DefaultDriveTrainHighGearThreshold = 6;
	private static final double DefaultTurningHighAmplitude = .65;
	private static final double DefaultTurningLowAmplitude = .35;
	private static final double DefaultLiftUpSpeed = .80;
	private static final double DefaultLiftDownSpeed = -.7;
	private static final double DefaultLiftManualSpeed = .25;
	private static final double DefaultLiftFastManualSpeed = 1.0;
	private static final double DefaultDriveStraightDecelDistance = 5;
	private static final double DefaultDriveStraightAccelDistance = 3;
	private static final double DefaultLowJoystickPower = 3;
	private static final double DefaultHighJoystickPower = 5;
	private static final double DefaultUsefulDriveTrainPower = .15;
	private static final double DefaultDriveStraightTolerance = .2;
	private static final double DefaultTractionControlMultiplier = .90;

	private int _totalSolenoid = 0;

	private Variables() {
		SmartDashboard.putNumber("DriveTrain Current Threshold", DefaultDriveTrainCurrentThreshold);
		SmartDashboard.putNumber("High FPS Turn Amplitude", DefaultTurningHighAmplitude);
		SmartDashboard.putNumber("Low FPS Turn Amplitude", DefaultTurningLowAmplitude);
		SmartDashboard.putNumber("Lift Up Speed", DefaultLiftUpSpeed);
		SmartDashboard.putNumber("Lift Down Speed", DefaultLiftDownSpeed);
		SmartDashboard.putNumber("Lift Manual Speed", DefaultLiftManualSpeed);
		SmartDashboard.putNumber("Lift Fast Manual Speed", DefaultLiftFastManualSpeed);
		SmartDashboard.putNumber("Drive Train Vel Threshold", DefaultDriveTrainHighGearThreshold);
		SmartDashboard.putNumber("Drive Straight Decel Distance", DefaultDriveStraightDecelDistance);
		SmartDashboard.putNumber("Drive Straight Accel Distance", DefaultDriveStraightAccelDistance);
		SmartDashboard.putNumber("kP_SPIN", kP_SPIN);
		SmartDashboard.putNumber("kI_SPIN", kI_SPIN);
		SmartDashboard.putNumber("kD_SPIN", kD_SPIN);
		SmartDashboard.putNumber("tolerance_SPIN", tolerance_SPIN);
		SmartDashboard.putNumber("Low Joystick Power", DefaultLowJoystickPower);
		SmartDashboard.putNumber("High Joystick Power", DefaultHighJoystickPower);
		SmartDashboard.putNumber("Lift Upward Decel Multiple", DefaultLiftUpwardDecelMultiple);
		SmartDashboard.putNumber("Lift Downward Decel Multiple", DefaultLiftDownwardDecelMultiple);
		SmartDashboard.putNumber("Lift Tolerance", DefaultLiftTolerance);
		SmartDashboard.putNumber("Useful DriveTrain Power", DefaultUsefulDriveTrainPower);
		SmartDashboard.putNumber("DriveStraight Tolerance", DefaultDriveStraightTolerance);
		SmartDashboard.putNumber("Traction Control Multiplier", DefaultTractionControlMultiplier);

		if (!SmartDashboard.containsKey("Autonomous Position Selector")) {
			SmartDashboard.putNumber("Autonomous Position Selector", 1);
		}
		if (!SmartDashboard.containsKey("Autonomous Prioritize Scale")) {
			SmartDashboard.putBoolean("Autonomous Prioritize Scale", false);
		}
		if (!SmartDashboard.containsKey("Autonomous Wait Time")) {
			SmartDashboard.putNumber("Autonomous Wait Time", 0);
		}
		if (!SmartDashboard.containsKey("Autonomous Auto Line")) {
			SmartDashboard.putBoolean("Autonomous Auto Line", false);
		}
		if (!SmartDashboard.containsKey("Autonomous Drag Race")) {
			SmartDashboard.putBoolean("Autonomous Drag Race", false);
		}
	}

	public static Variables getInstance() {
		if (_instance == null) {
			_instance = new Variables();
		}

		return _instance;
	}

	public double getDriveTrainCurrentThreshold() {
		return SmartDashboard.getNumber("DriveTrain Current Threshold", DefaultDriveTrainCurrentThreshold);
	}

	public double getTurningHighAmplitude() {
		return SmartDashboard.getNumber("High FPS Turn Amplitude", DefaultTurningHighAmplitude);
	}

	public double getTurningLowAmplitude() {
		return SmartDashboard.getNumber("Low FPS Turn Amplitude", DefaultTurningLowAmplitude);
	}

	public double getLiftUpSpeed() {
		return SmartDashboard.getNumber("Lift Up Speed", DefaultLiftUpSpeed);
	}

	public double getLiftDownSpeed() {
		return SmartDashboard.getNumber("Lift Down Speed", DefaultLiftDownSpeed);
	}

	public double getLiftManualSpeed() {
		return SmartDashboard.getNumber("Lift Manual Speed", DefaultLiftManualSpeed);
	}

	public double getLiftFastManualSpeed() {
		return SmartDashboard.getNumber("Lift Fast Manual Speed", DefaultLiftFastManualSpeed);
	}

	public double getDriveTrainHighGearThreshold() {
		return SmartDashboard.getNumber("Drive Train Vel Threshold", DefaultDriveTrainHighGearThreshold);
	}

	public double getDriveStraightDecelDistance() {
		return SmartDashboard.getNumber("Drive Straight Decel Distance", DefaultDriveStraightDecelDistance);
	}

	public double getDriveStraightAccelDistance() {
		return SmartDashboard.getNumber("Drive Straight Accel Distance", DefaultDriveStraightAccelDistance);
	}

	public double getSpinKP() {
		return SmartDashboard.getNumber("kP_SPIN", kP_SPIN);
	}

	public double getSpinKI() {
		return SmartDashboard.getNumber("kI_SPIN", kI_SPIN);
	}

	public double getSpinKD() {
		return SmartDashboard.getNumber("kD_SPIN", kD_SPIN);
	}

	public double getSpinTolerance() {
		return SmartDashboard.getNumber("tolerance_SPIN", tolerance_SPIN);
	}

	public double getHighJoystickPower() {
		return SmartDashboard.getNumber("High Joystick Power", DefaultHighJoystickPower);
	}

	public double getLowJoystickPower() {
		return SmartDashboard.getNumber("Low Joystick Power", DefaultLowJoystickPower);
	}

	public double getLiftUpwardDecelMultiple() {
		return SmartDashboard.getNumber("Lift Upward Decel Multiple", DefaultLiftUpwardDecelMultiple);
	}

	public double getLiftDownwardDecelMultiple() {
		return SmartDashboard.getNumber("Lift Downward Decel Multiple", DefaultLiftDownwardDecelMultiple);
	}

	public double getLiftTolerance() {
		return SmartDashboard.getNumber("Lift Tolerance", DefaultLiftTolerance);
	}

	public double getUsefulDriveTrainPower() {
		return SmartDashboard.getNumber("Useful DriveTrain Power", DefaultUsefulDriveTrainPower);
	}

	public double getDriveStraightTolerance() {
		return SmartDashboard.getNumber("DriveStraight Tolerance", DefaultDriveStraightTolerance);
	}

	public double getTractionControlMultiplier() {
		return SmartDashboard.getNumber("Traction Control Multiplier", DefaultTractionControlMultiplier);
	}

	public void outputVariables() {
		SmartDashboard.putNumber("DriveTrain Total Current", DriveTrain.getInstance().getTotalCurrent());
		SmartDashboard.putNumber("DriveTrain Joystick Power", OI.getInstance().getPower());
		SmartDashboard.putNumber("Lift Encoder Values", Lift.getInstance().getPosition());
		SmartDashboard.putNumber("Lift Desired Height", Lift.getInstance().getDesiredHeight());

		if (DriveTrain.getInstance().getTransmissionHighGear()) {
			SmartDashboard.putString("DriveTrain Gear", "High Gear");
		} else {
			SmartDashboard.putString("DriveTrain Gear", "Low Gear");
		}

		SmartDashboard.putNumber("AbsoluteCompassHeading", IMU.getInstance().getIMU().getAbsoluteCompassHeading());
		SmartDashboard.putNumber("CompassFieldStrength", IMU.getInstance().getIMU().getCompassFieldStrength());
		SmartDashboard.putNumber("CompassHeading", IMU.getInstance().getIMU().getCompassHeading());
		SmartDashboard.putNumber("FusedHeading", IMU.getInstance().getIMU().getFusedHeading());
		SmartDashboard.putNumber("Temp", IMU.getInstance().getIMU().getTemp());
		SmartDashboard.putNumber("FirmwareVersion", IMU.getInstance().getIMU().getFirmwareVersion());

		double[] q6 = new double[4];
		IMU.getInstance().getIMU().get6dQuaternion(q6);
		SmartDashboard.putString("6dQuaternion", q6[0] + " : " + q6[1] + " : " + q6[2] + " : " + q6[3]);

		double[] acc = new double[3];
		IMU.getInstance().getIMU().getAccelerometerAngles(acc);
		SmartDashboard.putString("AccelerometerAngles", acc[0] + " : " + acc[1] + " : " + acc[2]);

		short[] rawmag = new short[3];
		IMU.getInstance().getIMU().getRawMagnetometer(rawmag);
		SmartDashboard.putString("RawMagnetometer", rawmag[0] + " : " + rawmag[1] + " : " + rawmag[2]);

		double[] rawgyro = new double[3];
		IMU.getInstance().getIMU().getRawGyro(rawgyro);
		SmartDashboard.putString("RawGyro", rawgyro[0] + " : " + rawgyro[1] + " : " + rawgyro[2]);

		SmartDashboard.putBoolean("Left Intake Limit", LimitSwitches.getInstance().getLeftIntakeLimit());
		SmartDashboard.putBoolean("Right Intake Limit", LimitSwitches.getInstance().getRightIntakeLimit());
		SmartDashboard.putBoolean("Upper Lift Limit", LimitSwitches.getInstance().getUpperLiftLimit());
		SmartDashboard.putBoolean("Lower Lift Limit", LimitSwitches.getInstance().getLowerLiftLimit());
		SmartDashboard.putBoolean("Lift is Lifted", Lift.getInstance().isLifted());
		SmartDashboard.putBoolean("Lift is Locked", Lift.getInstance().isLocked());

		SmartDashboard.putNumber("Left Drivetrain Encoder Velocity", Encoders.getInstance().getLeftVelocity());
		SmartDashboard.putNumber("Right Drivetrain Encoder Velocity", Encoders.getInstance().getRightVelocity());

		SmartDashboard.putNumber("Left Drivetrain Encoder Position", Encoders.getInstance().getLeftDistance());
		SmartDashboard.putNumber("Right Drivetrain Encoder Position", Encoders.getInstance().getRightDistance());

		SmartDashboard.putNumber("RangeFinder Front", RangeFinder.getInstance().getDistanceFront());
		SmartDashboard.putNumber("RangeFinder Left", RangeFinder.getInstance().getDistanceLeft());
		SmartDashboard.putNumber("RangeFinder Right", RangeFinder.getInstance().getDistanceRight());
		SmartDashboard.putNumber("RangeFinder Back", RangeFinder.getInstance().getDistanceBack());

		SmartDashboard.putNumber("Solenoid Total", _totalSolenoid);

		SmartDashboard.putNumber("Yaw", IMU.getInstance().getYaw());
		SmartDashboard.putNumber("Pitch", IMU.getInstance().getPitch());
		SmartDashboard.putNumber("Roll", IMU.getInstance().getRoll());
	}

	public void addSolenoidFire() {
		_totalSolenoid++;
	}
}
