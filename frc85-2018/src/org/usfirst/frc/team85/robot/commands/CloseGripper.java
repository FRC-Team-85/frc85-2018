package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.subsystems.Gripper;

import edu.wpi.first.wpilibj.command.Command;

public class CloseGripper extends Command {

	public CloseGripper() {
		requires(Gripper.getInstance());
	}

	@Override
	protected void initialize() {
		Gripper.getInstance().close();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
