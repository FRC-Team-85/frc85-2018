package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraightTimer;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinExactDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	public Autonomous(String fieldKey) {
		addSequential(new DriveStraightTimer(1.0, 3));
		addSequential(new SpinExactDegrees(90));
		addSequential(new DriveStraightTimer(1.0, 3));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new DriveStraightTimer(.5, 2));
	}
}