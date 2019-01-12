package org.usfirst.frc.team85.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;

public class Globals {

	private static Globals _instance;
	private Compressor _compressor;
	private UsbCamera cam0, cam1;
	private Relay leds;

	private Globals() {
		_compressor = new Compressor(0);
		_compressor.setClosedLoopControl(true);
		_compressor.start();

		cam0 = CameraServer.getInstance().startAutomaticCapture(0);
		cam0.setResolution(320, 240);
		cam1 = CameraServer.getInstance().startAutomaticCapture(1);
		cam1.setResolution(320, 240);

		leds = new Relay(0, Direction.kForward);
		leds.set(Value.kOn);
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
