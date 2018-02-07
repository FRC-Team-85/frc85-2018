package org.usfirst.frc.team85.robot.auto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.usfirst.frc.team85.robot.Globals;

public class Diagnostics {

	File log;
	BufferedWriter out = null;
	private long _timestamp = System.currentTimeMillis();

	public void init() {
		try {
			close();

			String date = new java.text.SimpleDateFormat("yyyy-MM-ddy HHmmss")
					.format(new java.util.Date(System.currentTimeMillis()));
			log = new File("/home/lvuser/log " + date + ".csv");
			if (log.exists() == false) {
				log.createNewFile();
				out = new BufferedWriter(new FileWriter(log, true));
				out.append("Match Time,Gyro Angle,Gyro PID output");
				out.newLine();
			}
		} catch (Exception ex) {
			System.out.println("Error creating log file: " + ex.toString());
		}
		_timestamp = System.currentTimeMillis();
	}

	public void log(double correction) {
		try {

			String matchTime = Double.toString(System.currentTimeMillis() - _timestamp);
			String gyroAngle = Double.toString(Globals.getInstance().getGyro().getAngle());

			out.append(matchTime + "," + gyroAngle + "," + correction);
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
