package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves intake and wheels inside frame for protection
 */
public class ProtectIntake extends Command {

	private boolean _protect;

	public ProtectIntake(boolean protect) {
		requires(Intake.getInstance());
		_protect = protect;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Intake.getInstance().protect(_protect);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
