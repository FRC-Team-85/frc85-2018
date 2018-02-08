package org.usfirst.frc.team85.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	public Autonomous() {
		addParallel(new DriveStraight(0));
	}
}
