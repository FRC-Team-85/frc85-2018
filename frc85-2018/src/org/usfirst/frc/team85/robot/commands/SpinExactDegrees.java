package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class SpinExactDegrees extends Command {

	private static final double kP = 0.1, kI = 0.000001, kD = 0.2;
	private PIDController _pid;

	private double _targetAngle;
	private double _changeAngle;

	// left = positive, right = negative
	public SpinExactDegrees(double angle) {
		requires(DriveTrain.getInstance());
		_changeAngle = angle;
	}

	@Override
	protected void initialize() {
		super.initialize();
		_targetAngle = _changeAngle + IMU.getInstance().getFusedHeading();

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

		_pid.setAbsoluteTolerance(1);
		_pid.setSetpoint(_targetAngle);
		_pid.reset();
		_pid.enable();
	}

	public void applyCorrection(double correction) {
		double speed = .5;

		if (correction > 0) {
			DriveTrain.getInstance().drive(-(speed + Math.abs(correction) / 2), (speed + Math.abs(correction) / 2));
		} else {
			DriveTrain.getInstance().drive((speed + Math.abs(correction) / 2), -(speed + Math.abs(correction) / 2));
		}
	}

	@Override
	protected boolean isFinished() {
		return _pid.onTarget();
	}

}
