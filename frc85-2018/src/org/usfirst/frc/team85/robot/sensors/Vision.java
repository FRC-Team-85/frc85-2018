package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Vision {

	private static Vision _instance;

	private VisionThread thread;
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	private static final int FOV = 15; // degrees of field of view

	private DriveStraight _cmd = null;

	private Vision() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

		// thread = new VisionThread(camera, new VisionPipeLine(), pipeline -> {
		// if (!pipeline.filterContoursOutput().isEmpty()) {
		// Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
		// double error = (r.x + (r.width / 2)) - (IMG_WIDTH / 2);
		// double angle = error / IMG_WIDTH * FOV;
		// // convert pixel to angle via camera view
		// // negate for gyro
		// if (_cmd != null) {
		// _cmd.setAngle(-angle);
		// }
		// }
		// });
		// thread.start();
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
