package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class LiftClear extends Command {

	private boolean _clear;

	public LiftClear(boolean clear) {
		_clear = clear;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Lift.getInstance().setClear(_clear);
		Lift.getInstance().setDesiredHeight(Lift.getInstance().getPosition());
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
