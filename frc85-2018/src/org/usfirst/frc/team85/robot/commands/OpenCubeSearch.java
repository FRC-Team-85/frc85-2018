package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.commands.gripper.CloseGripper;
import org.usfirst.frc.team85.robot.commands.gripper.OpenGripper;
import org.usfirst.frc.team85.robot.commands.intake.ActivateIntake;
import org.usfirst.frc.team85.robot.commands.intake.ApplyIntake;
import org.usfirst.frc.team85.robot.commands.intake.FullyRetractIntake;
import org.usfirst.frc.team85.robot.commands.intake.IntakeLimitWait;
import org.usfirst.frc.team85.robot.commands.intake.PartialIntakeLimitWait;
import org.usfirst.frc.team85.robot.commands.intake.ProtectIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenCubeSearch extends CommandGroup {

	public OpenCubeSearch() {
		addSequential(new ProtectIntake(false));
		addSequential(new OpenGripper());
		addSequential(new ActivateIntake(1.0));

		addSequential(new PartialIntakeLimitWait());
		addSequential(new ApplyIntake(true));

		addSequential(new IntakeLimitWait());
		addSequential(new Wait(.1));
		addSequential(new CloseGripper());
		addSequential(new Wait(.05));
		addSequential(new ActivateIntake(0.0));
		addSequential(new Wait(.2));
		addSequential(new FullyRetractIntake());
	}
}
