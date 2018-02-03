package org.usfirst.frc.team85.robot.auto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.usfirst.frc.team85.robot.Globals;

public class Diagnostics {

	Globals struct = Globals.getInstance();

	File log;
	BufferedWriter out = null;

	public void init() {
		try {
			close();

			String date = new java.text.SimpleDateFormat("yyyy-MM-ddy HHmmss")
					.format(new java.util.Date(System.currentTimeMillis()));
			log = new File("/home/lvuser/log " + date + ".csv");
			if (log.exists() == false) {
				log.createNewFile();
				out = new BufferedWriter(new FileWriter(log, true));
				out.append("GyroPIDError");
				out.newLine();
			}
		} catch (Exception ex) {
			System.out.println("Error creating log file: " + ex.toString());
		}

	}

	public void log(ArrayList<Double> data) {
		try {

			String output = "";
			for (double d : data) {
				output += d + ",";
			}
			output = output.substring(0, output.length() - 1);

			out.append(output);
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
