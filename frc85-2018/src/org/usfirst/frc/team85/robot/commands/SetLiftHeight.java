package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class SetLiftHeight extends Command {

	private double _height;

	public SetLiftHeight(double height) {
		requires(Lift.getInstance());
		_height = height;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Lift.getInstance().setHeight(_height);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
