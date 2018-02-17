package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Gripper;
import org.usfirst.frc.team85.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Compressor;

public class Globals {

	private static Globals _instance;
	private Compressor _compressor;
	// private PowerDistributionPanel _powerDistributionPanel;

	private Globals() {
		DriveTrain.getInstance();
		OI.getInstance();
		// IMU.getInstance();

		// RangeFinder.getInstance();
		// Lift.getInstance();
		Intake.getInstance();
		Gripper.getInstance();
		Encoders.getInstance();

		_compressor = new Compressor(0);
		_compressor.setClosedLoopControl(true);
		_compressor.start();

		// _powerDistributionPanel = new
		// PowerDistributionPanel(Addresses.POWER_DISTRIBUTION_PANEL);
	}

	public static Globals getInstance() {
		if (_instance == null) {
			_instance = new Globals();
		}

		return _instance;
	}

	public Compressor getCompressor() {
		return _compressor;
	}

	// public PowerDistributionPanel getPDP() {
	// return _powerDistributionPanel;
	// }
}
