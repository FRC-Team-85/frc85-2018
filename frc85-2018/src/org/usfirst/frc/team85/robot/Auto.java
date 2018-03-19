package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.command.Command;

public class Auto extends Command {

	double _speed;

	public Auto(double speed, double timeout) {
		setTimeout(timeout);
		_speed = speed;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Drive.getInstance().setPower(_speed, _speed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		super.end();
		Drive.getInstance().setPower(0, 0);
	}
}