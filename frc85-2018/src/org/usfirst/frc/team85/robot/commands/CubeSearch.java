package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.commands.gripper.CloseGripper;
import org.usfirst.frc.team85.robot.commands.gripper.OpenGripper;
import org.usfirst.frc.team85.robot.commands.intake.ActivateIntake;
import org.usfirst.frc.team85.robot.commands.intake.ApplyIntake;
import org.usfirst.frc.team85.robot.commands.intake.FullyExtendIntake;
import org.usfirst.frc.team85.robot.commands.intake.IntakeLimitWait;
import org.usfirst.frc.team85.robot.commands.intake.ProtectIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CubeSearch extends CommandGroup {

	public CubeSearch() {
		addSequential(new FullyExtendIntake());
		addSequential(new Wait(.2));
		addParallel(new OpenGripper());
		addSequential(new ActivateIntake(1.0));

		addSequential(new IntakeLimitWait());
		addSequential(new Wait(.20));
		addSequential(new ProtectIntake(true));
		addSequential(new Wait(.05));
		addSequential(new CloseGripper());
		addSequential(new Wait(.05));
		addSequential(new ActivateIntake(0.0));
		addSequential(new ApplyIntake(false));
	}
}
