package org.usfirst.frc.team85.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Gripper;
import org.usfirst.frc.team85.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Diagnostics {

	File log;
	BufferedWriter out = null;
	private long _timestamp = System.currentTimeMillis();
	
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
				out.append("Match Time,Left Front,Left Back,Right Front,Right Back,Compressor");
				out.newLine();
			}
		} catch (Exception ex) {
			System.out.println("Error creating log file: " + ex.toString());
		}
		_timestamp = System.currentTimeMillis();
	}

	public void log() {
		try {

			String matchTime = Double.toString(System.currentTimeMillis() - _timestamp);
			String LF = Double.toString(DriveTrain.getInstance().getLeftFrontCurrent());
			String LB = Double.toString(DriveTrain.getInstance().getLeftBackCurrent());
			String RF = Double.toString(DriveTrain.getInstance().getRightFrontCurrent());
			String RB = Double.toString(DriveTrain.getInstance().getRightBackCurrent());
			String comp = Double.toString(Globals.getInstance().getCompressor().getCompressorCurrent());

			out.append(matchTime + "," + LF + "," + LB + "," + RF + "," + RB + "," + comp);
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
