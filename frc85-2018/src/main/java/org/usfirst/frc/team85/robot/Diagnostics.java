package org.usfirst.frc.team85.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.sensors.LimitSwitches;
import org.usfirst.frc.team85.robot.sensors.RangeFinder;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Gripper;
import org.usfirst.frc.team85.robot.subsystems.Intake;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;

public class Diagnostics {

	File log;
	BufferedWriter out = null;

	private int placeHolder = 0;

	public void init() {
		try {
			close();

			placeHolder = 0;

			String date = new java.text.SimpleDateFormat("yyyy-MM-ddy HHmmss")
					.format(new java.util.Date(System.currentTimeMillis()));
			log = new File("/home/lvuser/log " + date + ".csv");
			log.createNewFile();
			out = new BufferedWriter(new FileWriter(log, true));
			out.append("Time,Match Time,Left Joystick,Right Joystick,"
					+ "Left Velocity,Right Velocity,High Gear,Left Front,Left Back,Right Front,Right Back,"
					+ "Left Front Output,Left Back Output,Right Front Output,Right Back Output,"
					+ "Left Intake Limit,Right Intake Limit,"
					+ "Lift Position,Left One,Left Two, Right One,Right Two,Lower Lift Limit,Upper Lift Limit,"
					+ "Compressor,Total Solenoid Activations,Initial Yaw,Initial Pitch,Initial Roll,"
					+ "Yaw,Pitch,Roll,X Acceleration,Y Acceleration,Z Acceleration,Front,Back,Left,Right,Battery Voltage,"
					+ "OS_LIFT_PLATFORM_SWITCH,OS_LIFT_GROUND,OS_LIFT_LOCK,OS_LIFT_LOW_SCALE,"
					+ "OS_LIFT_MEDIUM_SCALE,OS_LIFT_HIGH_SCALE,OS_LIFT_DOUBLE_SCALE,"
					+ "OS_MISC_TOGGLE_GRIPPER,OS_MISC_INTAKE_PROTECT,OS_MISC_INTAKE_FORWARD,OS_MISC_INTAKE_REVERSE,"
					+ "OS_MISC_COMPRESSOR_ON,OS_MISC_COMPRESSOR_OFF,OS_MISC_CUBE_SEARCH,OS_MISC_EXHANGE_BUTTON,"
					+ "OS_MISC_LIFT_CLIMB,OS_MISC_OPEN_CUBE_SEARCH,"
					+ "Gripper Open,Intake Applied,Intake Protected,Lift Locked,"
					+ "Lift Up,Lift Down,Lift Fast Up,Lift Fast Down");
			out.newLine();
		} catch (Exception ex) {
			System.out.println("Error creating log file: " + ex.toString());
		}
	}

	public void log() {
		try {
			if (out == null) {
				init();
			}
			placeHolder++;
			String time = Integer.toString(placeHolder);
			String matchTime = Double.toString(DriverStation.getInstance().getMatchTime());

			// Driver
			String LJ = Double.toString(OI.getInstance().getLeftJoystick());
			String RJ = Double.toString(OI.getInstance().getRightJoystick());

			// Drive train
			String LFP = Double.toString(DriveTrain.getInstance().getLeftFrontPercent());
			String LBP = Double.toString(DriveTrain.getInstance().getLeftBackPercent());
			String RFP = Double.toString(DriveTrain.getInstance().getRightFrontPercent());
			String RBP = Double.toString(DriveTrain.getInstance().getRightBackPercent());

			String LV = Double.toString(Encoders.getInstance().getLeftVelocity());
			String RV = Double.toString(Encoders.getInstance().getRightVelocity());

			String gear = Boolean.toString(DriveTrain.getInstance().getTransmissionHighGear());

			String LF = Double.toString(DriveTrain.getInstance().getLeftFrontCurrent());
			String LB = Double.toString(DriveTrain.getInstance().getLeftBackCurrent());
			String RF = Double.toString(DriveTrain.getInstance().getRightFrontCurrent());
			String RB = Double.toString(DriveTrain.getInstance().getRightBackCurrent());
			// Lift
			String Pos = Double.toString(Lift.getInstance().getPosition());
			String L1 = Double.toString(Lift.getInstance().getLeftOneCurrent());
			String L2 = Double.toString(Lift.getInstance().getLeftTwoCurrent());
			String R1 = Double.toString(Lift.getInstance().getRightOneCurrent());
			String R2 = Double.toString(Lift.getInstance().getRightTwoCurrent());
			// Pneumatics
			String solenoid = Integer.toString(Variables.getInstance().getSolenoidFire());
			String comp = Double.toString(Globals.getInstance().getCompressor().getCompressorCurrent());
			// IMU
			String initialYaw = Double.toString(IMU.getInstance().getInitialYaw());
			String initialPitch = Double.toString(IMU.getInstance().getInitialPitch());
			String initialRoll = Double.toString(IMU.getInstance().getInitialRoll());
			String yaw = Double.toString(IMU.getInstance().getYaw());
			String pitch = Double.toString(IMU.getInstance().getPitch());
			String roll = Double.toString(IMU.getInstance().getRoll());

			String x = Double.toString(IMU.getInstance().getX());
			String y = Double.toString(IMU.getInstance().getY());
			String z = Double.toString(IMU.getInstance().getZ());
			// Rangefinders
			String F = Double.toString(RangeFinder.getInstance().getDistanceFront());
			String B = Double.toString(RangeFinder.getInstance().getDistanceBack());
			String L = Double.toString(RangeFinder.getInstance().getDistanceLeft());
			String R = Double.toString(RangeFinder.getInstance().getDistanceRight());

			String leftLS = Boolean.toString(LimitSwitches.getInstance().getLeftIntakeLimit());
			String rightLS = Boolean.toString(LimitSwitches.getInstance().getRightIntakeLimit());
			String LLS = Boolean.toString(LimitSwitches.getInstance().getLowerLiftLimit());
			String ULS = Boolean.toString(LimitSwitches.getInstance().getUpperLiftLimit());

			String voltage = Double.toString(RobotController.getBatteryVoltage());

			String OS_LIFT_PLATFORM_SWITCH = Boolean.toString(OI.getInstance().getLiftController().getRawButton(1));
			String OS_LIFT_GROUND = Boolean.toString(OI.getInstance().getLiftController().getRawButton(2));
			String OS_LIFT_LOCK = Boolean.toString(OI.getInstance().getLiftController().getRawButton(3));
			String OS_LIFT_LOW_SCALE = Boolean.toString(OI.getInstance().getLiftController().getRawButton(4));
			String OS_LIFT_MEDIUM_SCALE = Boolean.toString(OI.getInstance().getLiftController().getRawButton(5));
			String OS_LIFT_HIGH_SCALE = Boolean.toString(OI.getInstance().getLiftController().getRawButton(6));
			String OS_LIFT_DOUBLE_SCALE = Boolean.toString(OI.getInstance().getLiftController().getRawButton(7));

			String OS_MISC_TOGGLE_GRIPPER = Boolean.toString(OI.getInstance().getLiftController().getRawButton(1));
			String OS_MISC_INTAKE_PROTECT = Boolean.toString(OI.getInstance().getLiftController().getRawButton(2));
			String OS_MISC_INTAKE_FORWARD = Boolean.toString(OI.getInstance().getLiftController().getRawButton(3));
			String OS_MISC_INTAKE_REVERSE = Boolean.toString(OI.getInstance().getLiftController().getRawButton(4));
			String OS_MISC_COMPRESSOR_ON = Boolean.toString(OI.getInstance().getLiftController().getRawButton(5));
			String OS_MISC_COMPRESSOR_OFF = Boolean.toString(OI.getInstance().getLiftController().getRawButton(6));
			String OS_MISC_CUBE_SEARCH = Boolean.toString(OI.getInstance().getLiftController().getRawButton(9));
			String OS_MISC_EXHANGE_BUTTON = Boolean.toString(OI.getInstance().getLiftController().getRawButton(10));
			String OS_MISC_LIFT_CLIMB = Boolean.toString(OI.getInstance().getLiftController().getRawButton(11));
			String OS_MISC_VISION_SEARCH = Boolean.toString(OI.getInstance().getLiftController().getRawButton(8));

			String gripperOpen = Boolean.toString(Gripper.getInstance().isOpen());
			String intakeApplied = Boolean.toString(Intake.getInstance().isApplied());
			String intakeProtect = Boolean.toString(Intake.getInstance().isProtected());
			String liftLock = Boolean.toString(Lift.getInstance().isLocked());

			double liftJoystickAxis1 = OI.getInstance().getLiftController().getRawAxis(1);
			double liftJoystickAxis2 = OI.getInstance().getLiftController().getRawAxis(0);

			String liftUp = Boolean.toString(liftJoystickAxis1 == -1);
			String liftDown = Boolean.toString(liftJoystickAxis1 == 1);
			String liftFastUp = Boolean.toString(liftJoystickAxis2 == 1);
			String liftFastDown = Boolean.toString(liftJoystickAxis2 == -1);

			out.append(time + "," + matchTime + "," + LJ + "," + RJ + "," + LV + "," + RV + "," + gear + "," + LF + ","
					+ LB + "," + RF + "," + RB + "," + LFP + "," + LBP + "," + RFP + "," + RBP + "," + leftLS + ","
					+ rightLS + "," + Pos + "," + L1 + "," + L2 + "," + R1 + "," + R2 + "," + LLS + "," + ULS + ","
					+ comp + "," + solenoid + "," + initialYaw + "," + initialPitch + "," + initialRoll + "," + yaw
					+ "," + pitch + "," + roll + "," + x + "," + y + "," + z + "," + F + "," + B + "," + L + "," + R
					+ "," + voltage + "," + OS_LIFT_PLATFORM_SWITCH + "," + OS_LIFT_GROUND + "," + OS_LIFT_LOCK + ","
					+ OS_LIFT_LOW_SCALE + "," + OS_LIFT_MEDIUM_SCALE + "," + OS_LIFT_HIGH_SCALE + ","
					+ OS_LIFT_DOUBLE_SCALE + "," + OS_MISC_TOGGLE_GRIPPER + "," + OS_MISC_INTAKE_PROTECT + ","
					+ OS_MISC_INTAKE_FORWARD + "," + OS_MISC_INTAKE_REVERSE + "," + OS_MISC_COMPRESSOR_ON + ","
					+ OS_MISC_COMPRESSOR_OFF + "," + OS_MISC_CUBE_SEARCH + "," + OS_MISC_EXHANGE_BUTTON + ","
					+ OS_MISC_LIFT_CLIMB + "," + OS_MISC_VISION_SEARCH + "," + gripperOpen + "," + intakeApplied + ","
					+ intakeProtect + "," + liftLock + "," + liftUp + "," + liftDown + "," + liftFastUp + ","
					+ liftFastDown);

			out.newLine();
		} catch (Exception ex) {
			System.out.println("Error writing diagnostic log: " + ex.toString());
		}
	}

	public void close() {
		if (out != null) {
			try {
				out.close();
				out = null;
			} catch (Exception ex) {
				System.out.println("Error closing file: " + ex.toString());
			}
		}
	}
}
