
package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleTransmission extends Command {

	public ToggleTransmission() {
		requires(DriveTrain.getInstance());
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (DriveTrain.getInstance().getTransmissionHighGear()) {
			DriveTrain.getInstance().setHighGear(false);
		} else {
			DriveTrain.getInstance().setHighGear(true);
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
