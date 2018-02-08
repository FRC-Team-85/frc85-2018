package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class CloseGripper extends TimedCommand {

	public CloseGripper() {
		super(1);
		requires(Robot.gripper);
	}

	@Override
	protected void initialize() {
		Robot.gripper.close();
	}
}
