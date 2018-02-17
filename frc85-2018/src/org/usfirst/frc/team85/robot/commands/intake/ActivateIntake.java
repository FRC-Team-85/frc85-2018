package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateIntake extends Command {

	private double _power;

	public ActivateIntake(double power) {
		requires(Intake.getInstance());
		_power = power;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Intake.getInstance().setPower(_power);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
