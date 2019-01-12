package org.usfirst.frc.team85.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DelayedCommand extends CommandGroup {

	public DelayedCommand(Command command, double time) {
		addSequential(new Wait(time));
		addSequential(command);
	}
}
