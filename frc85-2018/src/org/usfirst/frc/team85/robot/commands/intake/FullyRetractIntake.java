package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FullyRetractIntake extends CommandGroup {

	public FullyRetractIntake() {
		addSequential(new ProtectIntake(true));
		addSequential(new Wait(.3));
		addSequential(new ApplyIntake(false));
	}
}
