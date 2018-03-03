package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.commands.gripper.OpenGripper;
import org.usfirst.frc.team85.robot.commands.intake.ActivateIntake;
import org.usfirst.frc.team85.robot.commands.intake.FullyExtendIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class EjectCube extends CommandGroup {

	public EjectCube() {
		addSequential(new FullyExtendIntake());
		addSequential(new OpenGripper());
		addSequential(new ActivateIntake(-1.0));
	}
}
