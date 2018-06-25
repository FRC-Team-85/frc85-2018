package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;

import edu.wpi.first.wpilibj.SerialPort;

public class Vision {

	private static Vision _instance;

	private Thread thread;
	private static final int IMG_WIDTH = 320;
	private static final int FOV = 30; // degrees of field of view

	private DriveStraight _cmd = null;

	private int _index, _centerX;
	private double _error, _angle;

	private Vision() {
		try {
			SerialPort cam = new SerialPort(115200, SerialPort.Port.kUSB);
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!Thread.interrupted()) {
						try {
							for (String input : cam.readString().split("\n")) {
								if (input != null && !input.equals("")) {
									if (input.startsWith("I") || input.startsWith("E")) {
										System.out.println(input);
										continue;
									}

									_index = Integer.parseInt(input.substring(0, 1));
									_centerX = Integer
											.parseInt(input.substring(input.indexOf(" "), input.indexOf(".")).trim());

									_error = _centerX - (IMG_WIDTH / 2);
									_angle = -_error / IMG_WIDTH * FOV;

									System.out
											.println("Index: " + _index + " Center: " + _centerX + " Angle: " + _angle);
									if (_cmd != null && _index == 0) {
										_cmd.setAngle(_angle);
									}
								}
							}
							Thread.sleep(20);
						} catch (Exception ex) {
							System.out.println("Error Reading Serial Port: " + ex.toString());
						}
					}
				}
			});
			thread.start();
		} catch (Exception ex) {
			System.out.println("Error Serial Port: " + ex.toString());
		}
	}

	public static Vision getInstance() {
		if (_instance == null) {
			_instance = new Vision();
		}
		return _instance;
	}

	public void setCommand(DriveStraight cmd) {
		_cmd = cmd;
	}
}
