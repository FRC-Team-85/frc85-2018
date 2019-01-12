package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.commands.drivetrain.AbsoluteDirection;
import org.usfirst.frc.team85.robot.sensors.RangeFinder;

import edu.wpi.first.wpilibj.command.Command;

public class CorrectBasedOnRange extends Command {

	AbsoluteDirection direction;
	double min, max, distance;

	public CorrectBasedOnRange(AbsoluteDirection direction, double min, double max, double distance) {
		this.direction = direction;
		this.min = min;
		this.max = max;
		this.distance = distance;
	}

	@Override
	protected void initialize() {
		super.initialize();
		double range = 0;
		switch (direction) {
		case BACKWARD:
			range = RangeFinder.getInstance().getDistanceBack();
			break;
		case FORWARD:
			range = RangeFinder.getInstance().getDistanceFront();
			break;
		case LEFT:
			range = RangeFinder.getInstance().getDistanceLeft();
			break;
		case RIGHT:
			range = RangeFinder.getInstance().getDistanceRight();
			break;
		}

		double correction = 0;
		if (range < min) {
			correction = -1 * Math.atan((min - range) / distance);
		} else if (range > max) {
			correction = Math.atan((min - range) / distance);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
