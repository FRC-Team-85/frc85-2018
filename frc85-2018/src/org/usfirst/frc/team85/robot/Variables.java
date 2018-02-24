package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.LimitSwitches;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Variables {

	private static Variables _instance;

	public static final double LIFT_GROUND = 100;
	public static final double LIFT_PLATFORM = 4000;
	public static final double LIFT_SWITCH = 10500;
	public static final double LIFT_LOCK = 9500;
	public static final double LIFT_SCALE_LOW = 21500;
	public static final double LIFT_SCALE = 30000;
	public static final double LIFT_CLIMB = 34300;
	public static final double LIFT_SCALE_HIGH = 37700;
	public static final double LIFT_SCALE_HIGH_DOUBLE = 39300;

	public static final double LIFT_TOLERANCE = 400;

	private static final double DefaultDriveTrainCurrentThreshold = 155;
	private static final double DefaultTurningHighAmplitude = .65;
	private static final double DefaultTurningLowAmplitude = .35;
	private static final double DefaultLiftUpSpeed = .4;
	private static final double DefaultLiftDownSpeed = -.25;

	private Variables() {
		SmartDashboard.putNumber("DriveTrain Current Threshold", DefaultDriveTrainCurrentThreshold);
		SmartDashboard.putNumber("High FPS Turn Amplitude", DefaultTurningHighAmplitude);
		SmartDashboard.putNumber("Low FPS Turn Amplitude", DefaultTurningLowAmplitude);
		SmartDashboard.putNumber("Lift Up Speed", DefaultLiftUpSpeed);
		SmartDashboard.putNumber("Lift Down Speed", DefaultLiftDownSpeed);
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

		/*
		 * SmartDashboard.putNumber("AbsoluteCompassHeading",
		 * IMU.getInstance().getIMU().getAbsoluteCompassHeading());
		 * SmartDashboard.putNumber("CompassFieldStrength",
		 * IMU.getInstance().getIMU().getCompassFieldStrength());
		 * SmartDashboard.putNumber("CompassHeading",
		 * IMU.getInstance().getIMU().getCompassHeading());
		 * SmartDashboard.putNumber("FusedHeading",
		 * IMU.getInstance().getIMU().getFusedHeading());
		 * SmartDashboard.putNumber("Temp", IMU.getInstance().getIMU().getTemp());
		 * SmartDashboard.putNumber("FirmwareVersion",
		 * IMU.getInstance().getIMU().getFirmwareVersion());
		 * 
		 * double[] q6 = new double[4]; IMU.getInstance().getIMU().get6dQuaternion(q6);
		 * SmartDashboard.putString("6dQuaternion", q6[0] + " : " + q6[1] + " : " +
		 * q6[2] + " : " + q6[3]);
		 * 
		 * double[] acc = new double[3];
		 * IMU.getInstance().getIMU().getAccelerometerAngles(acc);
		 * SmartDashboard.putString("AccelerometerAngles", acc[0] + " : " + acc[1] +
		 * " : " + acc[2]);
		 * 
		 * double[] gyro = new double[3]; IMU.getInstance().getIMU().getAccumGyro(gyro);
		 * SmartDashboard.putString("AccumGyro", gyro[0] + " : " + gyro[1] + " : " +
		 * gyro[2]);
		 * 
		 * short[] rawmag = new short[3];
		 * IMU.getInstance().getIMU().getRawMagnetometer(rawmag);
		 * SmartDashboard.putString("RawMagnetometer", rawmag[0] + " : " + rawmag[1] +
		 * " : " + rawmag[2]);
		 * 
		 * double[] rawgyro = new double[3];
		 * IMU.getInstance().getIMU().getRawGyro(rawgyro);
		 * SmartDashboard.putString("RawGyro", rawgyro[0] + " : " + rawgyro[1] + " : " +
		 * rawgyro[2]);
		 * 
		 * double[] ypr = new double[3];
		 * IMU.getInstance().getIMU().getYawPitchRoll(ypr);
		 * SmartDashboard.putString("YawPitchRoll", ypr[0] + " : " + ypr[1] + " : " +
		 * ypr[2]);
		 * 
		 */

		SmartDashboard.putBoolean("Left Intake Limit", LimitSwitches.getInstance().getLeftIntakeLimit());
		SmartDashboard.putBoolean("Right Intake Limit", LimitSwitches.getInstance().getRightIntakeLimit());
		SmartDashboard.putBoolean("Upper Lift Limit", LimitSwitches.getInstance().getUpperLiftLimit());
		SmartDashboard.putBoolean("Lower Lift Limit", LimitSwitches.getInstance().getLowerLiftLimit());
		SmartDashboard.putBoolean("Lift is Lifted", Lift.getInstance().isLifted());
		SmartDashboard.putBoolean("Lift is Locked", Lift.getInstance().isLocked());

		SmartDashboard.putNumber("Left Drivetrain Encoder Velocity", Encoders.getInstance().getLeftVelocity());
		SmartDashboard.putNumber("Right Drivetrain Encoder Velocity", Encoders.getInstance().getRightVelocity());
	}
}
