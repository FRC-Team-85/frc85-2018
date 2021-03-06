package org.usfirst.frc.team85.robot.commands.lift;

import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLift extends Command {

	private double _power;

	public MoveLift(double power) {
		requires(Lift.getInstance());
		_power = power;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Lift.getInstance().setOverrideSpeed(_power);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
