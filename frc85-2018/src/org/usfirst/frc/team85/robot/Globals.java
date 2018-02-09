package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.RangeFinder;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Globals {

	private static Globals _instance;

	private static ADXRS450_Gyro _gyro;
	private static RangeFinder _rangeFinder;

	private Globals() {
		OI.getInstance();
		DriveTrain.getInstance();
		// Lift.getInstance();
		// Gripper.getInstance();
		// Intake.getInstance();

		// _gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
		// _rangeFinder = RangeFinder.getInstance();

		SmartDashboard.putData(DriveTrain.getInstance());
		// SmartDashboard.putData(_lift);
	}

	public static Globals getInstance() {
		if (_instance == null) {
			_instance = new Globals();
		}
		return _instance;
	}

	public ADXRS450_Gyro getGyro() {
		return _gyro;
	}

	public RangeFinder getRangeFinder() {
		return _rangeFinder;
	}
}
