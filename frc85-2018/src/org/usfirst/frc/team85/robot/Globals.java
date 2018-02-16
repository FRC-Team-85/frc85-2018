package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.sensors.RangeFinder;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Compressor;

public class Globals {

	private static Globals _instance;
	private Compressor _compressor;
	private DriverAssistCameras _das;

	private Globals() {
		DriveTrain.getInstance();
		OI.getInstance();
		IMU.getInstance();
		RangeFinder.getInstance();

		_compressor = new Compressor(0);
		_compressor.setClosedLoopControl(true);
		_compressor.start();

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

	public DriverAssistCameras getDriverAssistCameras() {
		return _das;
	}
}
