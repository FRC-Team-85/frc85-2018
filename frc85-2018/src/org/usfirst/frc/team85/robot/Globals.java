package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.RangeFinder;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Gripper;
import org.usfirst.frc.team85.robot.subsystems.Intake;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Globals {

	private static Globals _instance;

	private static DriveTrain _driveTrain;
	private static Lift _lift;
	private static Gripper _gripper;
	private static Intake _intake;

	private static ADXRS450_Gyro _gyro;
	private static RangeFinder _rangeFinder;

	private static OI oi;

	private Globals() {
		oi = OI.getInstance();
		_driveTrain = DriveTrain.getInstance();
		// _lift = Lift.getInstance();
		// _gripper = Gripper.getInstance();
		// _intake = Intake.getInstance();

		// _gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
		// _rangeFinder = RangeFinder.getInstance();

		SmartDashboard.putData(_driveTrain);
		// SmartDashboard.putData(_lift);
	}

	public static Globals getInstance() {
		if (_instance == null) {
			_instance = new Globals();
		}
		return _instance;
	}

	public DriveTrain getDriveTrain() {
		return _driveTrain;
	}

	public Lift getLift() {
		return _lift;
	}

	public Gripper getGripper() {
		return _gripper;
	}

	public Intake getIntake() {
		return _intake;
	}

	public ADXRS450_Gyro getGyro() {
		return _gyro;
	}

	public RangeFinder getRangeFinder() {
		return _rangeFinder;
	}

	public OI getOI() {
		return oi;
	}

}
