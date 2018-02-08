package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Globals;
import org.usfirst.frc.team85.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	public DriveWithJoystick() {
		requires(Globals.getInstance().getDriveTrain());
	}

	@Override
	protected void execute() {
		double[] speeds = OI.getInstance().getSpeedInput();
		Globals.getInstance().getDriveTrain().drive(speeds[0], speeds[1]);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Globals.getInstance().getDriveTrain().drive(0, 0);
	}
}
