package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.commands.CubeSearch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionCubeSearch extends CommandGroup {

	public VisionCubeSearch() {
		addParallel(new DriveStraight(.25, 2).setVisionTrack().setRangeFinderDistance(AbsoluteDirection.FORWARD));
		addSequential(new CubeSearch());
	}
}
