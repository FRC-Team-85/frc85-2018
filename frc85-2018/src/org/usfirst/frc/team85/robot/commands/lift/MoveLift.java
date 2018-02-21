package org.usfirst.frc.team85.robot.commands.lift;

import org.usfirst.frc.team85.robot.OI;
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
			if (OI.getInstance().isPowerLift()) {
				if (_power > 0) {
					Lift.getInstance().setPower(1.0);
				} else {
					Lift.getInstance().setPower(-.75);
				}
			} else {
				Lift.getInstance().setPower(_power);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
