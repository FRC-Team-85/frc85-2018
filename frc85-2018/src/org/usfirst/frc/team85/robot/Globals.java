package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.sensors.RangeFinder;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Compressor;

public class Globals {

	private static Globals _instance;
	private static Compressor _compressor;

	private Globals() {
		DriveTrain.getInstance();
		OI.getInstance();
		IMU.getInstance();
		RangeFinder.getInstance();

		_compressor = new Compressor(0);
		_compressor.setClosedLoopControl(true);
		_compressor.start();

		new DriverAssistCameras();
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
}
