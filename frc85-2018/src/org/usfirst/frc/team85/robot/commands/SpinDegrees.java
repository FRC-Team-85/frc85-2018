package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Globals;

import edu.wpi.first.wpilibj.command.Command;

public class SpinDegrees extends Command {

	private static final int _tolerance = 5;
	private double _angle;

	public SpinDegrees(double angle) {
		_angle = angle + Globals.getInstance().getGyro().getAngle();
	}

	@Override
	protected void execute() {
		Globals.getInstance().getDriveTrain().drive(-1, 1);
	}

	@Override
	protected boolean isFinished() {
		return (Globals.getInstance().getGyro().getAngle() <= _angle + _tolerance
				&& Globals.getInstance().getGyro().getAngle() >= _angle - _tolerance);
	}

}
