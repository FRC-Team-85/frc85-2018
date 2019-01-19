package org.usfirst.frc.team85.robot.commands.gripper;

import org.usfirst.frc.team85.robot.subsystems.Gripper;

import edu.wpi.first.wpilibj.command.Command;

public class OpenGripper extends Command {

	public OpenGripper() {
		requires(Gripper.getInstance());
	}

	@Override
	protected void initialize() {
		Gripper.getInstance().open();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
