package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.sensors.RangeFinder;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {

	private static final double kP = 1 / 90, kI = 0.5, kD = 0.0;
	private PIDController _pid;

	private double _speed;
	private double _distance;

	public DriveStraight(double speed, double distance) {
		requires(DriveTrain.getInstance());
		_speed = speed;

		// given ultrasonic is in front of robot
		// distance between wall and front to stop command
		_distance = distance;
	}

	@Override
	protected void initialize() {
		_pid = new PIDController(kP, kI, kD, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			@Override
			public double pidGet() {
				return IMU.getInstance().getFusedHeading();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
		}, d -> applyCorrection(d));

		_pid.setAbsoluteTolerance(2);
		_pid.setSetpoint(IMU.getInstance().getFusedHeading());

		_pid.reset();
		_pid.enable();
	}

	public void applyCorrection(double correction) {
		if (correction > 0) {
			DriveTrain.getInstance().drive(_speed - Math.sin(Math.abs(correction)), _speed);
		} else {
			DriveTrain.getInstance().drive(_speed, _speed - Math.sin(Math.abs(correction)));
		}
	}

	@Override
	protected boolean isFinished() {
		return (RangeFinder.getInstance().getDistance() <= _distance);
	}

	@Override
	protected void end() {
		_pid.disable();
		DriveTrain.getInstance().drive(0, 0);
	}
}
