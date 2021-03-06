package org.usfirst.frc.team85.robot.commands.lift;

import org.usfirst.frc.team85.robot.commands.intake.FullyRetractIntake;
import org.usfirst.frc.team85.robot.subsystems.Intake;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class SetLiftHeight extends Command {

	private double _height;

	public SetLiftHeight(double height) {
		requires(Lift.getInstance());
		_height = height;
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (Lift.getInstance().isLifted() && !DriverStation.getInstance().isAutonomous()
				&& (Intake.getInstance().isApplied() || !Intake.getInstance().isProtected())) {
			Scheduler.getInstance().add(new FullyRetractIntake());
		}
		Lift.getInstance().setDesiredHeight(_height);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
