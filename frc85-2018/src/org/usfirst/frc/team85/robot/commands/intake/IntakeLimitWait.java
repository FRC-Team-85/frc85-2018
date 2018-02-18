package org.usfirst.frc.team85.robot.commands.intake;

import org.usfirst.frc.team85.robot.sensors.LimitSwitches;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeLimitWait extends Command {

	@Override
	protected boolean isFinished() {
		return LimitSwitches.getInstance().getLeftIntakeLimit() && LimitSwitches.getInstance().getRightIntakeLimit();
	}

}
