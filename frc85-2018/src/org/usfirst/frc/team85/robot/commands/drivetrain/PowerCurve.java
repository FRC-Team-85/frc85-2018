package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class PowerCurve extends Command {

	private double _leftSpeed, _rightSpeed, _targetAngle, _changeAngle;

	/**
	 * 
	 * 
	 * @param speed
	 *            Motor speed coming into turn in percentage
	 * @param angle
	 *            Duration of turn in terms of degrees: +left,-right
	 */
	public PowerCurve(double speed, double angle) {
		requires(DriveTrain.getInstance());

		double speedFT = speed * Variables.maxSpeed;
		double minR = speedFT * speedFT / Variables.µ / Variables.g;
		double outerV = speedFT * (1 + (Variables.wheelSpan / 2 / minR));
		double innerV = speedFT * (1 - (Variables.wheelSpan / 2 / minR));

		double maxOuterV = speedFT * (1 + (Variables.wheelSpan / 2 / minR)) / Variables.maxSpeed;

		_changeAngle = angle;
		if (angle > 0) {
			_leftSpeed = (innerV / Variables.maxSpeed) / maxOuterV * speed;
			_rightSpeed = (outerV / Variables.maxSpeed) / maxOuterV * speed;
		} else {
			_rightSpeed = (innerV / Variables.maxSpeed) / maxOuterV * speed;
			_leftSpeed = (outerV / Variables.maxSpeed) / maxOuterV * speed;
		}
	}

	@Override
	protected void initialize() {
		super.initialize();
		_targetAngle = _changeAngle + IMU.getInstance().getFusedHeading();
	}

	@Override
	protected void execute() {
		super.execute();
		DriveTrain.getInstance().drive(_leftSpeed, _rightSpeed);
	}

	@Override
	protected boolean isFinished() {
		if (_changeAngle > 0) {
			return IMU.getInstance().getFusedHeading() >= _targetAngle;
		} else {
			return IMU.getInstance().getFusedHeading() <= _targetAngle;
		}
	}

	@Override
	protected void end() {
		super.end();
		DriveTrain.getInstance().drive(0, 0);
	}
}
