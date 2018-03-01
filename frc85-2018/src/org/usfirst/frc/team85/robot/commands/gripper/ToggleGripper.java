package org.usfirst.frc.team85.robot.commands.gripper;

import org.usfirst.frc.team85.robot.subsystems.Gripper;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleGripper extends Command {

	public ToggleGripper() {
		requires(Gripper.getInstance());
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (Gripper.getInstance().isOpen()) {
			Gripper.getInstance().close();
		} else {
			Gripper.getInstance().open();
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
