package org.usfirst.frc.team85.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionApproachAndBackup extends CommandGroup {

	public VisionApproachAndBackup(double speed, double distance) {
		addSequential(new DriveStraight(speed, distance - 3).setAcceleration(true, false));
		addSequential(new DriveStraight(.35, 3, 3).setAcceleration(false, true).setVisionTrack());
		addSequential(new DriveStraight(-.25, 1));
	}
}
