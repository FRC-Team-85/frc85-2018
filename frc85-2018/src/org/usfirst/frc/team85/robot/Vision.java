package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

public class Vision {

	private static Vision _instance;

	private Thread thread;
	private static final int IMG_WIDTH = 320;
	private static final int FOV = 30; // degrees of field of view

	private DriveStraight _cmd = null;

	private Vision() {
		try {
			SerialPort cam = new SerialPort(115200, SerialPort.Port.kUSB);
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!Thread.interrupted()) {
						try {
							String input = cam.readString();
							System.out.println("Serial Input: " + input);
							if (input != null && !input.equals("")) {
								int index = Integer.parseInt(input.substring(0, 1));
								int centerX = Integer
										.parseInt(input.substring(input.indexOf(" "), input.indexOf(".")).trim());

								double error = centerX - (IMG_WIDTH / 2);
								double angle = -error / IMG_WIDTH * FOV;

								System.out.println("Index: " + index + " Center: " + centerX + " Angle: " + angle);
								if (_cmd != null && index == 0) {
									_cmd.setAngle(angle);
								}
							}

							System.out.println(input);
						} catch (Exception ex) {
							System.out.println("Error Reading Serial Port: " + ex.toString());
						}
						Timer.delay(.02);
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
