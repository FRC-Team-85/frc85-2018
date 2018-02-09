package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.subsystems.Gripper;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class OpenGripper extends TimedCommand {

	public OpenGripper() {
		super(1);
		requires(Gripper.getInstance());
	}

	@Override
	protected void initialize() {
		Gripper.getInstance().open();
	}
}
