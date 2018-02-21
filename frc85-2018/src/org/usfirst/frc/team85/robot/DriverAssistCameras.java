package org.usfirst.frc.team85.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class DriverAssistCameras {
	private UsbCamera _forwardCamera;
	// private UsbCamera _reverseCamera;
	private UsbCamera _currentCamera;

	public DriverAssistCameras() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(200);
					_forwardCamera = CameraServer.getInstance().startAutomaticCapture(0);
					Thread.sleep(200);
					// _reverseCamera =
					// CameraServer.getInstance().startAutomaticCapture(Addresses.REVERSE_CAMERA);
					// Thread.sleep(200);
				} catch (Exception ex) {
					System.out.println("Error initializing cameras: " + ex.toString());
				}

				_currentCamera = _forwardCamera;
				Mat frame = new Mat();
				CvSource outputStream = CameraServer.getInstance().putVideo("Drive", 160, 120);

				while (!Thread.interrupted()) {
					try {
						CameraServer.getInstance().getVideo(_currentCamera).grabFrame(frame);
						outputStream.putFrame(frame);
					} catch (Exception ex) {
						System.out.println(ex.toString());
					}
				}
			}
		}).start();
	}

	public void setForward() {
		_currentCamera = _forwardCamera;
	}

	public void setReverse() {
		// _currentCamera = _reverseCamera;
	}
}
