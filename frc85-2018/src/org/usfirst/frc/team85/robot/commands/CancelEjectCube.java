package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.commands.intake.ActivateIntake;
import org.usfirst.frc.team85.robot.commands.intake.FullyRetractIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CancelEjectCube extends CommandGroup {

	public CancelEjectCube() {
		addSequential(new ActivateIntake(0));
		addSequential(new FullyRetractIntake());
	}
}
