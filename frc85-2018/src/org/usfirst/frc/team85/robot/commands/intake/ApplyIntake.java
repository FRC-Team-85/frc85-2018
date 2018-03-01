package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.subsystems.Intake;
import org.usfirst.frc.team85.robot.subsystems.Lift;

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
		if (!Lift.getInstance().isLifted()) {
			Intake.getInstance().apply(_apply);
		} else {
			Intake.getInstance().apply(false);
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
