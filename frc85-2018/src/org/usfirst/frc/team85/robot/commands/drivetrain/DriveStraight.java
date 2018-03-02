package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends Command {

	private static final double kP = 0.1, kI = 0.000001, kD = 0.2;

	private PIDController _pid;

	private double _speed;
	private double _distance;

	public DriveStraight(double speed, double distance) {
		requires(DriveTrain.getInstance());
		_speed = speed;
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

		_pid.setOutputRange(-25, 25);

		_pid.reset();
		_pid.enable();
	}

	public void applyCorrection(double correction) {
		SmartDashboard.putNumber("Correction Value", correction);
		double error = Math.abs(
				_distance - (Encoders.getInstance().getLeftDistance() + Encoders.getInstance().getRightDistance()) / 2);

		double applySpeed = _speed;
		if (error < Variables.getInstance().getDriveStraightDecelDistance()) {
			applySpeed *= (error / Variables.getInstance().getDriveStraightDecelDistance()) + .1;
		} else if (error > _distance - Variables.getInstance().getDriveStraightAccelDistance()) {
			applySpeed *= (error / Variables.getInstance().getDriveStraightAccelDistance()) + .2;
		}

		if (applySpeed > 1) {
			applySpeed = 1;
		}
		if (applySpeed < -1) {
			applySpeed = -1;
		}

		if (correction > 0) {
			DriveTrain.getInstance().drive(applySpeed - Math.abs(correction), applySpeed);
		} else {
			DriveTrain.getInstance().drive(applySpeed, applySpeed - Math.abs(correction));
		}
	}

	@Override
	protected boolean isFinished() {
		double error = Math.abs(_distance
				- Math.abs((Encoders.getInstance().getLeftDistance() + Encoders.getInstance().getRightDistance()) / 2));

		if (error < .5) {
			return true;
		}

		return false;
		/*
		 * if (_speed > 0) { return (Encoders.getInstance().getLeftDistance() +
		 * Encoders.getInstance().getRightDistance()) / 2 > _distance; } else { return
		 * (Encoders.getInstance().getLeftDistance() +
		 * Encoders.getInstance().getRightDistance()) / 2 < -_distance; }
		 */
	}

	@Override
	protected void end() {
		_pid.disable();
		DriveTrain.getInstance().drive(0, 0);
		Encoders.getInstance().driveEncoderReset();
	}
}
