package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraightTimer;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinExactDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	public Autonomous() {

	}

	public void init(String fieldKey) {
		build(fieldKey);
		start();
	}

	private void build(String fieldKey) {
		addSequential(new DriveStraightTimer(1.0, 5));
		addSequential(new SpinExactDegrees(180));
		addSequential(new DriveStraightTimer(1.0, 5));
	}
}