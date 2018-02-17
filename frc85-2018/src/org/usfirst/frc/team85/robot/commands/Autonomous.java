package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraightTimer;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinExactDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	private String _fieldKey;

	public Autonomous() {
		addSequential(new DriveStraightTimer(1.0, 3));
		addSequential(new SpinExactDegrees(90));
		addSequential(new DriveStraightTimer(1.0, 3));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new DriveStraightTimer(.5, 2));
	}

	public void setKey(String fieldKey) {
		_fieldKey = fieldKey;
	}
}