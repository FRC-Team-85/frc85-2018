package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.OI;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	public DriveWithJoystick() {
		requires(DriveTrain.getInstance());
	}

	@Override
	protected void execute() {
		double[] speeds = OI.getInstance().getSpeedInput();
		DriveTrain.getInstance().drive(speeds[0], speeds[1]);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		DriveTrain.getInstance().drive(0, 0);
	}
}
