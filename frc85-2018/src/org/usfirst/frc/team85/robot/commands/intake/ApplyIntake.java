package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves intake wheels towards the center to be applied to the cubes
 */
public class ApplyIntake extends Command {

	private boolean _apply;

	public ApplyIntake(boolean apply) {
		requires(Intake.getInstance());
		_apply = apply;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Intake.getInstance().apply(_apply);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
