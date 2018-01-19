package org.usfirst.frc.team85.robot.auto;

import java.util.ArrayList;

public class Auto {

	String _fieldKey;
	private static ArrayList<Action> _actions = new ArrayList<Action>();

	public Auto(String fieldKey) {
		_fieldKey = fieldKey;
		_actions.add(new Action(ActionType.STRAIGHT, new double[] { .85, 10 },
				new InputSource(InputType.GYRO, new double[] { 0 }),
				new InputSource(InputType.RANGE, new double[] { 3, 4 })));
	}

	public static void terminate() {
		if (_actions.size() >= 0) {
			_actions.remove(0);
		}
	}

	public double[] autoTick() {
		return _actions.get(0).returnSpeed();
	}
}
