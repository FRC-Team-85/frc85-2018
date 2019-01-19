package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreePointTurn extends CommandGroup {

	double speed;
	double strafeDistance;
	double backDistance;
	double forwardDistance;
	double turnDegrees;
	boolean finished = false;

	public ThreePointTurn(double speed, double strafeDistance, double backDistance) {
		requires(DriveTrain.getInstance());

		this.strafeDistance = strafeDistance;
		this.speed = speed;
		this.backDistance = backDistance;
		turnDegrees = Math.asin(strafeDistance / backDistance);
		forwardDistance = Math.tan(turnDegrees) / strafeDistance;

		addSequential(new DriveStraight(-speed, backDistance));
		addSequential(new SpinExactDegrees(turnDegrees));
		addSequential(new DriveStraight(speed, forwardDistance));
		addSequential(new SpinExactDegrees(-turnDegrees));
	}
}
