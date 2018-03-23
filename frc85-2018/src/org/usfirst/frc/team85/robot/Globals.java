package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;

public class Globals {

	private static Globals _instance;
	private Compressor _compressor;

	private Globals() {
		_compressor = new Compressor(0);
		_compressor.setClosedLoopControl(true);
		_compressor.start();

		CameraServer.getInstance().startAutomaticCapture(0);
		CameraServer.getInstance().startAutomaticCapture(1);
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
