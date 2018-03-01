package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.subsystems.Intake;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Starts intake motors
 */
public class ActivateIntake extends Command {

	private double _power;

	public ActivateIntake(double power) {
		requires(Intake.getInstance());
		_power = power;
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (!Lift.getInstance().isLifted()) {
			Intake.getInstance().setPower(_power);
		} else {
			Intake.getInstance().setPower(0);
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
