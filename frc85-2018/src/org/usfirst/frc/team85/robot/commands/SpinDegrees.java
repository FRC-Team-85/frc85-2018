package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.OI;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class SpinDegrees extends Command {

	private double _targetAngle;
	private double _changeAngle;

	// left = positive, right = negative
	public SpinDegrees(double angle) {
		requires(DriveTrain.getInstance());
		_changeAngle = angle * .8; // arbitrary value to deal with momentum
	}

	@Override
	protected void initialize() {
		super.initialize();
		_targetAngle = _changeAngle + IMU.getInstance().getFusedHeading();
	}

	@Override
	protected void execute() {
		if (_targetAngle > IMU.getInstance().getFusedHeading()) {
			DriveTrain.getInstance().drive(-1, 1);
		} else {
			DriveTrain.getInstance().drive(1, -1);
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

	@Override
	protected void end() {
		DriveTrain.getInstance().drive(0, 0);
	}

}
