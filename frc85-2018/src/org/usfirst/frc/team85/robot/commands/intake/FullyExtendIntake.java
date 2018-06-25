package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FullyExtendIntake extends CommandGroup {

	public FullyExtendIntake() {
		addSequential(new ProtectIntake(false));
		addSequential(new Wait(.2));
		addSequential(new ApplyIntake(true));
	}
}
