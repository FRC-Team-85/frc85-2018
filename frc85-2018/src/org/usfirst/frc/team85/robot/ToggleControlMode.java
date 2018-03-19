package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleControlMode extends Command {

	public ToggleControlMode() {

	}

	@Override
	protected void initialize() {
		super.initialize();
		if (Drive.getInstance().isTank()) {
			Drive.getInstance().setTank(false);
		} else {
			
			Drive.getInstance().setTank(true);
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
