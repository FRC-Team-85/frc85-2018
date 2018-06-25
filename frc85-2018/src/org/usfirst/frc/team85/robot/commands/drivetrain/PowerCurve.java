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
	 * @param radius
	 *            Radius of the path you desire the robot to take (to
	 *            the outer edge of the robot in order to prevent cases
	 *            where the speed is over 1) in feet
	 * @param angle
	 *            Duration of turn in terms of degrees: +left,-right
	 */
	public PowerCurve(double radius, double angle) {
		requires(DriveTrain.getInstance());
		
		_changeAngle = angle;
		
		double outerSpeed = Math.pow(Variables.mu * Variables.g * radius, .5) / Variables.maxSpeed;
		double innerSpeed = Math.pow(Variables.mu * Variables.g * (radius - Variables.wheelSpan), .5) / Variables.maxSpeed;
		
		if (angle > 0) {
			_leftSpeed = innerSpeed;
			_rightSpeed = outerSpeed;
		} else {
			_rightSpeed = innerSpeed;
			_leftSpeed = outerSpeed;
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
