package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.OI;
import org.usfirst.frc.team85.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	public DriveWithJoystick() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void execute() {
		double[] speeds = OI.getInstance().getSpeedInput();
		Robot.driveTrain.drive(speeds[0], speeds[1]);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveTrain.drive(0, 0);
	}
}
