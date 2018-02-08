package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class OpenGripper extends TimedCommand {

	public OpenGripper() {
		super(1);
		requires(Robot.gripper);
	}

	@Override
	protected void initialize() {
		Robot.gripper.open();
	}
}
