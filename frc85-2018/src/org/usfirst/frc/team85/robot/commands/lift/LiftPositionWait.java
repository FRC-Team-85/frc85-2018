package org.usfirst.frc.team85.robot.commands.lift;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class LiftPositionWait extends Command {

	private boolean _underLoad;

	public LiftPositionWait(boolean underLoad) {
		_underLoad = underLoad;
	}

	@Override
	protected boolean isFinished() {
		double error = Math.abs(Lift.getInstance().getDesiredHeight() - Lift.getInstance().getPosition());
		if (_underLoad) {
			return (error <= Variables.getInstance().getLiftTolerance() + 500);
		}
		return (error <= Variables.getInstance().getLiftTolerance());
	}
}
