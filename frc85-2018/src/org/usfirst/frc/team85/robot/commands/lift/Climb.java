package org.usfirst.frc.team85.robot.commands.lift;

import org.usfirst.frc.team85.robot.Variables;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Climb extends CommandGroup {

	public Climb() {
		addSequential(new SetLiftHeight(Variables.LIFT_LOCK));
		addSequential(new LiftPositionWait(true));
		addSequential(new LockLift(true));
		addSequential(new SetLiftHeight(-1));
	}
}
