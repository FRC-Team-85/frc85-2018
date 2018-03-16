package org.usfirst.frc.team85.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;

public class NetworkTables {

	private static NetworkTables _instance;

	private Thread thread;
	private NetworkTable _table;

	private double[] _values;

	private NetworkTables() {
		_values = new double[] { -2.0, -2.0, -2.0, -2.0 };

		try {
			_table = NetworkTableInstance.getDefault().getTable("GRIP/myContoursReport");
			NetworkTableEntry entry = _table.getEntry("key");

			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!Thread.interrupted()) {
						_values = entry.getDoubleArray(_values);
						Timer.delay(.1);
					}
				}
			});
			thread.start();
		} catch (Exception ex) {
			System.out.println("Error in NetworkTable connection: " + ex.toString());
		}
	}

	public static NetworkTables getInstance() {
		if (_instance == null) {
			_instance = new NetworkTables();
		}
		return _instance;
	}

	public double[] getValues() {
		return _values;
	}
}
