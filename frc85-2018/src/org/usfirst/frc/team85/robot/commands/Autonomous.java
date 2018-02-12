package org.usfirst.frc.team85.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	// Goes in a square
	public Autonomous() {
		addSequential(new DriveStraight(.85, 150));
		addSequential(new SpinExactDegrees(90));
	}
}
