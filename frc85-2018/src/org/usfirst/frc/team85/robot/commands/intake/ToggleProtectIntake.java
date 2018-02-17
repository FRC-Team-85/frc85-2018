package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Moves intake and wheels inside frame for protection
 */
public class ToggleProtectIntake extends Command {

	public ToggleProtectIntake() {
		requires(Intake.getInstance());
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (Intake.getInstance().isProtected()) {
			Scheduler.getInstance().add(new FullyExtendIntake());
		} else {
			Scheduler.getInstance().add(new FullyRetractIntake());
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
