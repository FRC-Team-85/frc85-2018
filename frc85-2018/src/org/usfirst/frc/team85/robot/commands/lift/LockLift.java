package org.usfirst.frc.team85.robot.commands.lift;

import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class LockLift extends Command {

	private boolean _lock;

	public LockLift(boolean lock) {
		requires(Lift.getInstance());
		_lock = lock;
	}

	@Override
	protected void initialize() {
		Lift.getInstance().lock(_lock);
		super.initialize();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
