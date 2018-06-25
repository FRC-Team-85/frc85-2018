package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.commands.CubeSearch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionCubeSearch extends CommandGroup {

	public VisionCubeSearch(double speed, double distance) {
		addParallel(new DriveStraight(speed, distance).setVisionTrack());
		addSequential(new CubeSearch());
	}
}
