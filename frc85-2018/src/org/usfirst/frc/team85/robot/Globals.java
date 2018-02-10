package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

public class Globals {

	private static Globals _instance;

	private Globals() {
		OI.getInstance();
		DriveTrain.getInstance();
		// Lift.getInstance();
		// Gripper.getInstance();
		// Intake.getInstance();

		// _gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
		// RangeFinder.getInstance();
		IMU.getInstance();
	}

	public static Globals getInstance() {
		if (_instance == null) {
			_instance = new Globals();
		}
		return _instance;
	}
}
