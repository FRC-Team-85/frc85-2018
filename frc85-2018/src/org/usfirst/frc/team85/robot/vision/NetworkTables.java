package org.usfirst.frc.team85.robot.vision;

import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;

public class NetworkTables {

	private static NetworkTables _instance;

	private Thread thread;
	private NetworkTable _table;

	private DriveStraight _cmd = null;

	private static final int IMG_WIDTH = 320;
	private static final int FOV = 40;

	private NetworkTables() {
		try {
			_table = NetworkTableInstance.getDefault().getTable("GRIP/myContoursReport");
			NetworkTableEntry foundx = _table.getEntry("foundx");
			NetworkTableEntry foundw = _table.getEntry("foundw");

			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!Thread.interrupted()) {
						double x = foundx.getDouble(0);
						double w = foundw.getDouble(0);
						double center = x + w / 2;
						double error = center - (IMG_WIDTH / 2);
						double angle = -error / IMG_WIDTH * FOV;

						System.out.println("Center: " + center + " Angle: " + angle);
						if (_cmd != null) {
							_cmd.setAngle(angle);
						}
						Timer.delay(.1);
					}
				}
			});
			thread.start();
		} catch (

		Exception ex) {
			System.out.println("Error in NetworkTable connection: " + ex.toString());
		}
	}

	public static NetworkTables getInstance() {
		if (_instance == null) {
			_instance = new NetworkTables();
		}
		return _instance;
	}

	public void setCommand(DriveStraight cmd) {
		_cmd = cmd;
	}
}
