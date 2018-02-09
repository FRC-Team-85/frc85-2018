package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Globals;
import org.usfirst.frc.team85.robot.OI;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class SpinDegrees extends Command {

	private static final int _tolerance = 5;
	private double _angle;

	public SpinDegrees(double angle) {
		_angle = angle + Globals.getInstance().getGyro().getAngle();
	}

	@Override
	protected void execute() {
		if (_angle > Globals.getInstance().getGyro().getAngle()) {
			DriveTrain.getInstance().drive(.5, -.5);
		} else {
			DriveTrain.getInstance().drive(-.5, .5);
		}
	}

	@Override
	protected boolean isFinished() {
		if (!OI.getInstance().isFPS()) {
			return true;
		}

		return (Globals.getInstance().getGyro().getAngle() <= _angle + _tolerance
				&& Globals.getInstance().getGyro().getAngle() >= _angle - _tolerance);
	}

}
