package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class LiftClear extends Command {

	public LiftClear(boolean clear) {
		Lift.getInstance().setClear(clear);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
