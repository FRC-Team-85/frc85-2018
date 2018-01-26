package org.usfirst.frc.team85.robot.auto;

import java.util.ArrayList;

public class Auto {

	String _fieldKey;
	private static ArrayList<Action> _movementActions = new ArrayList<Action>();
	// private static ArrayList<Action> _intakeActions = new ArrayList<Action>();

	public Auto(String fieldKey) {
		_fieldKey = fieldKey;

		_movementActions.add(new Action(ActionType.ACCEL, new double[] { .50, .2 },
				new InputSource(InputType.GYRO, new double[] { 0 }), null, null));

		_movementActions.add(new Action(ActionType.STRAIGHT, new double[] { .50 },
				new InputSource(InputType.GYRO, new double[] { 0 }), null,
				new InputSource(InputType.ENCODER, new double[] { 20, 20 })));

		_movementActions.add(new Action(ActionType.DECEL, new double[] { 0, .2 },
				new InputSource(InputType.GYRO, new double[] { 0 }), null, null));
	}

	public static void terminate() {
		if (_movementActions.size() >= 0) {
			_movementActions.remove(0);
		}
	}

	public double[] autoTick() {
		double[] movement = _movementActions.get(0).returnSpeed();

		return movement;
	}
}
