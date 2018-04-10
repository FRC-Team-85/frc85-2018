package org.usfirst.frc.team85.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;

public class Globals {

	private static Globals _instance;
	private Compressor _compressor;

	private UsbCamera cam0, cam1;

	private Globals() {
		_compressor = new Compressor(0);
		_compressor.setClosedLoopControl(true);
		_compressor.start();

		cam0 = CameraServer.getInstance().startAutomaticCapture();
		cam0.setResolution(320, 240);
		cam1 = CameraServer.getInstance().startAutomaticCapture();
		cam1.setResolution(320, 240);
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

	public UsbCamera getCamera(int cam) {
		if (cam == 0) {
			return cam0;
		} else {
			return cam1;
		}
	}
}
