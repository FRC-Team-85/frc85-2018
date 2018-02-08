package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Globals;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class OpenGripper extends TimedCommand {

	public OpenGripper() {
		super(1);
		requires(Globals.getInstance().getGripper());
	}

	@Override
	protected void initialize() {
		Globals.getInstance().getGripper().open();
	}
}
