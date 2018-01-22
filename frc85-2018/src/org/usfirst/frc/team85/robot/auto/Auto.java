package org.usfirst.frc.team85.robot.auto;

import java.util.ArrayList;

public class Auto {

	String _fieldKey;
	private static ArrayList<Action> _movementActions = new ArrayList<Action>();
	// private static ArrayList<Action> _intakeActions = new ArrayList<Action>();

	public Auto(String fieldKey) {
		_fieldKey = fieldKey;

		_movementActions.add(new Action(ActionType.ACCEL, new double[] { 0, .85, 5 },
				new InputSource(InputType.RANGE, new double[] { 3, 4 }),
				new InputSource(InputType.GYRO, new double[] { 0 }), null));

		_movementActions.add(new Action(ActionType.STRAIGHT, new double[] { .85, 100 },
				new InputSource(InputType.GYRO, new double[] { 0 }),
				new InputSource(InputType.RANGE, new double[] { 3, 4 }),
				new InputSource(InputType.RANGE, new double[] { 2, 30 })));
	}

	public static void terminate() {
		if (_movementActions.size() >= 0) {
			_movementActions.remove(0);
		}
	}

	public void autoTick() {
		double[] movement = _movementActions.get(0).returnSpeed();

		if (movement != null) {
			// set Drivetrain values
		}
	}
}
