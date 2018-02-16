package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Globals;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleCamera extends Command {

	@Override
	protected void initialize() {
		super.initialize();
		Globals.getInstance().getDriverAssistCameras().toggle();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
