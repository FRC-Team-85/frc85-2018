package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FullyRetractIntake extends CommandGroup {

	public FullyRetractIntake() {
		addSequential(new ApplyIntake(false));
		addSequential(new Wait(.2));
		addSequential(new ProtectIntake(true));
	}
}
