package org.usfirst.frc.team85.robot.vision;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team85.robot.DriverAssistCameras;
import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Vision {

	private static Vision _instance;

	private VisionThread thread;
	private static final int IMG_WIDTH = 320;
	// private static final int IMG_HEIGHT = 240;
	private static final int FOV = 40; // degrees of field of view

	private DriveStraight _cmd = null;

	private Vision() {
		try {
			UsbCamera camera = DriverAssistCameras.getInstance().getVisionCamera();
			thread = new VisionThread(camera, new GripPipeline(), pipeline -> {
				if (!pipeline.filterContoursOutput().isEmpty()) {
					Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
					double error = (r.x + (r.width / 2)) - (IMG_WIDTH / 2);
					double angle = error / IMG_WIDTH * FOV;
					if (_cmd != null) {
						_cmd.setAngle(-angle);
					}
					System.out.println(angle);
					SmartDashboard.putNumber("Vision Tracking Angle", angle);
				}
			});
			thread.start();
		} catch (Exception e) {

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
