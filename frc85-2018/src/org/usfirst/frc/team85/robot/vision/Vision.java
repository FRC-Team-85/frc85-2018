package org.usfirst.frc.team85.robot.vision;

import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

public class Vision {

	private static Vision _instance;

	private Thread thread;
	private static final int IMG_WIDTH = 80;
	private static final int FOV = 40; // degrees of field of view

	private DriveStraight _cmd = null;

	private Vision() {
		// try {
		// UsbCamera camera = Globals.getInstance().getCamera(0);
		//
		// thread = new VisionThread(camera, new GripPipeline(), pipeline -> {
		// if (!pipeline.filterContoursOutput().isEmpty()) {
		// Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
		// double error = (r.x + (r.width / 2)) - (IMG_WIDTH / 2);
		// System.out.print("Center: " + (r.x + (r.width / 2)));
		// double angle = error / IMG_WIDTH * FOV;
		// if (_cmd != null) {
		// _cmd.setAngle(-angle);
		// }
		// System.out.println(" Angle: " + angle);
		// SmartDashboard.putNumber("Vision Tracking Angle", angle);
		// }
		// });
		// thread.start();
		// } catch (Exception e) {
		//
		// }

		try {
			SerialPort cam = new SerialPort(921600, SerialPort.Port.kUSB);
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!Thread.interrupted()) {
						try {
							System.out.println(cam.readString());
						} catch (Exception e) {
							System.out.println("Error Reading Serial Port: " + e.getMessage());
						}

						// double x = foundx.getDouble(0);
						// double w = foundw.getDouble(0);
						// double center = x + w / 2;
						// double error = center - (IMG_WIDTH / 2);
						// double angle = -error / IMG_WIDTH * FOV;
						//
						// System.out.println("Center: " + center + " Angle: " + angle);
						// if (_cmd != null) {
						// _cmd.setAngle(angle);
						// }
						Timer.delay(.005);
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
