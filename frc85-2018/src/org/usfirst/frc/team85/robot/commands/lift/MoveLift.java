package org.usfirst.frc.team85.robot.commands.lift;

import org.usfirst.frc.team85.robot.sensors.LimitSwitches;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLift extends Command {

	private double _power;

	public MoveLift(double power) {
		requires(Lift.getInstance());
		_power = power;
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (!(LimitSwitches.getInstance().getLowerLiftLimit() && (_power < 0))
				&& !(LimitSwitches.getInstance().getUpperLiftLimit() && (_power > 0))) {
			Lift.getInstance().setPower(_power);
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
