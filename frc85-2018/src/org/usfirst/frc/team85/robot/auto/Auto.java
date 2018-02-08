package org.usfirst.frc.team85.robot.auto;

import java.util.ArrayList;

import org.usfirst.frc.team85.robot.auto.InputSource.EncoderSource;
import org.usfirst.frc.team85.robot.auto.InputSource.GyroSource;
import org.usfirst.frc.team85.robot.auto.action.Action;
import org.usfirst.frc.team85.robot.auto.action.DriveStraightAction;

public class Auto {

	String _fieldKey;
	private static ArrayList<Action> _movementActions = new ArrayList<Action>();

	public Auto(String fieldKey) {
		_fieldKey = fieldKey;

		_movementActions.add(new DriveStraightAction(.85, new GyroSource(0), null, new EncoderSource(40)));
	}

	public static void terminate() {
		if (_movementActions.size() >= 0) {
			_movementActions.remove(0);
		}
	}

	public double[] autoTick() {
		return _movementActions.get(0).returnSpeed();
	}
}