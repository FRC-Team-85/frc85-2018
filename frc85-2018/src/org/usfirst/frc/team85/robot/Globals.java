package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Globals {

	private static Globals _instance;
	private Compressor _compressor;
	private PowerDistributionPanel _powerDistributionPanel;
	private DriverAssistCameras _das;

	private Globals() {
		DriveTrain.getInstance();
		OI.getInstance();
		IMU.getInstance();

		// RangeFinder.getInstance();
		// Lift.getInstance();
		// Intake.getInstance();
		// Gripper.getInstance();
		// Encoders.getInstance();

		_compressor = new Compressor(0);
		_compressor.setClosedLoopControl(true);
		_compressor.start();

		_powerDistributionPanel = new PowerDistributionPanel(Addresses.POWER_DISTRIBUTION_PANEL);

		_das = new DriverAssistCameras();
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

	public PowerDistributionPanel getPDP() {
		return _powerDistributionPanel;
	}

	public DriverAssistCameras getDriverAssistCameras() {
		return _das;
	}
}
