package org.usfirst.frc.team85.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Gripper;
import org.usfirst.frc.team85.robot.subsystems.Intake;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Diagnostics {

	File log;
	BufferedWriter out = null;
	
	public int totalSolenoid = 0;

	public void init() {
		try {
			close();

			String date = new java.text.SimpleDateFormat("yyyy-MM-ddy HHmmss")
					.format(new java.util.Date(System.currentTimeMillis()));
			log = new File("/home/lvuser/log " + date + ".csv");
			if (log.exists() == false) {
				log.createNewFile();
				out = new BufferedWriter(new FileWriter(log, true));
				out.append("Match Time,"
						+ "Left Joystick,Right Joystick"
						+ "Left Velocity,Right Velocity,High Gear,Left Front,Left Back,Right Front,Right Back,"
						+ "Left One,Left Two, Right One,Right Two,"
						+ "Compressor,Total Solenoid Activations"
						+ "Yaw,Pitch,Roll,X Acceleration,Y Acceleration,Z Acceleration");
				out.newLine();
			}
		} catch (Exception ex) {
			System.out.println("Error creating log file: " + ex.toString());
		}
	}

	public void log() {
		try {

			String matchTime = Double.toString(DriverStation.getInstance().getMatchTime());
			//Driver
			String LJ = Double.toString(OI.getInstance().getLeftJoystick());
			String RJ = Double.toString(OI.getInstance().getRightJoystick());
			//Drive train
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
			//Lift
			String Pos = Double.toString(Lift.getInstance().getPosition());
			String L1 = Double.toString(Lift.getInstance().getLeftOneCurrent());
			String L2 = Double.toString(Lift.getInstance().getLeftTwoCurrent());
			String R1 = Double.toString(Lift.getInstance().getRightOneCurrent());
			String R2 = Double.toString(Lift.getInstance().getRightTwoCurrent());
			//Pneumatics
			String solenoid = Integer.toString(totalSolenoid);
			String comp = Double.toString(Globals.getInstance().getCompressor().getCompressorCurrent());
			//IMU
			String yaw = Double.toString(IMU.getInstance().getYaw());
			String pitch = Double.toString(IMU.getInstance().getPitch());
			String roll = Double.toString(IMU.getInstance().getRoll());
			
			String x = Double.toString(IMU.getInstance().getX());
			String y = Double.toString(IMU.getInstance().getY());
			String z = Double.toString(IMU.getInstance().getZ());

			out.append(matchTime + 
					"," + LJ + "," + RJ + "," + LFP + "," + LBP + "," + RFP + "," + RBP +
					"," + LV + ","+ RV + "," + gear + "," + LF + "," + LB + "," + RF + "," + RB + 
					"," + L1 + "," + L2 + "," + R1 + "," + R2 +
					"," + comp + "," + solenoid +
					"," + yaw + "," + pitch + "," + roll + "," + x + "," + y + "," + z);
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
	
	public void solenoidLog() {
		SmartDashboard.putNumber("Solenoid Total", totalSolenoid);
	}
}
