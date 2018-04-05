package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class SweepingTurn extends Command {

	private double _targetAngle;
	private double _changeAngle;

	private double _leftSpeed = 0;
	private double _rightSpeed = 0;

	public SweepingTurn(double speed, double radius, double angle) {
		requires(DriveTrain.getInstance());
		_changeAngle = angle;

		double ratio = (radius - Variables.wheelSpan) / radius;

		if (angle > 0 || (angle < 0 && speed < 0)) {
			_leftSpeed = ratio * speed;
			_rightSpeed = speed;
		} else {
			_leftSpeed = speed;
			_rightSpeed = ratio * speed;
		}
	}

	@Override
	protected void initialize() {
		super.initialize();
		_targetAngle = _changeAngle + IMU.getInstance().getFusedHeading();
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
		Encoders.getInstance().driveEncoderReset();
		DriveTrain.getInstance().drive(0, 0);
	}
}
