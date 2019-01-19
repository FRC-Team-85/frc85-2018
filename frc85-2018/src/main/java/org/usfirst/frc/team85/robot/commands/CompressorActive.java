package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Globals;

import edu.wpi.first.wpilibj.command.Command;

public class CompressorActive extends Command {

	private boolean _active;

	public CompressorActive(boolean active) {
		_active = active;
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (_active) {
			Globals.getInstance().getCompressor().start();
		} else {
			Globals.getInstance().getCompressor().stop();
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
