package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.OI;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class SpinDegrees extends Command {

	// private static final int _tolerance = 5;
	private double _targetAngle;
	private double _changeAngle;

	public SpinDegrees(double angle) {
		_changeAngle = angle;
		_targetAngle = angle + IMU.getInstance().getFusedHeading();

		if (!OI.getInstance().isFPS()) {
			end();
		}
	}

	@Override
	protected void execute() {
		if (_targetAngle > IMU.getInstance().getFusedHeading()) {
			DriveTrain.getInstance().drive(-.5, .5);
		} else {
			DriveTrain.getInstance().drive(.5, -.5);
		}
	}

	@Override
	protected boolean isFinished() {
		if (!OI.getInstance().isFPS()) {
			return true;
		}

		if (_changeAngle > 0) {
			return (IMU.getInstance().getFusedHeading() >= _targetAngle);
		} else {
			return (IMU.getInstance().getFusedHeading() <= _targetAngle);
		}
	}

}
