package org.usfirst.frc.team85.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	public Autonomous() {
		addSequential(new SpinExactDegrees(270));
		addSequential(new DriveStraight(1.00, 150));
	}
}
