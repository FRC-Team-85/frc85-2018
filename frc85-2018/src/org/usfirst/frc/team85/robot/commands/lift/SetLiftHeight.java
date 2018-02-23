package org.usfirst.frc.team85.robot.commands.lift;

import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class SetLiftHeight extends Command {

	private double _height;
	private double _tolerance = 500;

	public SetLiftHeight(double height) {
		requires(Lift.getInstance());
		_height = height;
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (_height > Lift.getInstance().getPosition()) {
			Lift.getInstance().setPower(.4);
		} else {
			Lift.getInstance().setPower(-.4);
		}
	}

	@Override
	protected void execute() {
		super.execute();
		if (_height > Lift.getInstance().getPosition()) {
			Lift.getInstance().setPower(.4);
		} else {
			Lift.getInstance().setPower(-.4);
		}

		if (Lift.getInstance().getPosition() < _height + 4 * _tolerance
				&& Lift.getInstance().getPosition() > _height - 4 * _tolerance) {
			Lift.getInstance().setPower(.2);
		}
	}

	@Override
	protected boolean isFinished() {
		Lift.getInstance().setPower(0.0);
		return Lift.getInstance().getPosition() < _height + _tolerance
				&& Lift.getInstance().getPosition() > _height - _tolerance;
	}

}
