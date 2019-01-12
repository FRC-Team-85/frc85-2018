
package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class SetTransmissionHigh extends Command {

	private boolean _trans;

	public SetTransmissionHigh(boolean trans) {
		requires(DriveTrain.getInstance());
		_trans = trans;
	}

	@Override
	protected void initialize() {
		super.initialize();
		DriveTrain.getInstance().setHighGear(_trans);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
