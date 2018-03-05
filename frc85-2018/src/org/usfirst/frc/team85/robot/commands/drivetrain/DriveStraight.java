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
	private double _heading;
	private AbsoluteDirection _direction = null;

	public DriveStraight(double speed, double distance) {
		requires(DriveTrain.getInstance());
		Encoders.getInstance().driveEncoderReset();
		_speed = speed;
		_distance = Math.abs(distance);
		_heading = IMU.getInstance().getFusedHeading();
	}

	public DriveStraight(double speed, double distance, AbsoluteDirection direction) {
		requires(DriveTrain.getInstance());
		Encoders.getInstance().driveEncoderReset();
		_speed = speed;
		_distance = Math.abs(distance);
		_direction = direction;
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

		if (_direction != null) {
			double currentHeading = IMU.getInstance().getFusedHeading();
			double initHeading = IMU.getInstance().getInitialHeading();
			int diff = (int) (currentHeading - initHeading);
			int rot = diff / 360;

			switch (_direction) {
			case BACKWARD:
				_heading = initHeading + (rot * 360) - 180;
				break;
			case FORWARD:
				_heading = initHeading + (rot * 360);
				break;
			case LEFT:
				_heading = initHeading + (rot * 360) + 90;
				break;
			case RIGHT:
				_heading = initHeading + (rot * 360) - 90;
				break;
			default:
				_heading = currentHeading;
				break;
			}
		}

		_pid.setSetpoint(_heading);
		_pid.setAbsoluteTolerance(2);

		_pid.setOutputRange(-25, 25);

		_pid.reset();
		_pid.enable();
	}

	public void applyCorrection(double correction) {
		SmartDashboard.putNumber("Correction Value", correction);
		double error = Math.abs(_distance)
				- (Math.abs(Encoders.getInstance().getLeftDistance() + Encoders.getInstance().getRightDistance()) / 2);

		double applySpeed = _speed;
		if (error < Variables.getInstance().getDriveStraightDecelDistance()) {
			applySpeed *= (error / Variables.getInstance().getDriveStraightDecelDistance());
			applySpeed += Variables.getInstance().getUsefulDriveTrainPower() * (Math.abs(_speed) / _speed);
		} else if (error > _distance - Variables.getInstance().getDriveStraightAccelDistance()) {
			applySpeed *= (error / Variables.getInstance().getDriveStraightAccelDistance());
			applySpeed += Variables.getInstance().getUsefulDriveTrainPower() * (Math.abs(_speed) / _speed);
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
		double error = Math.abs(_distance)
				- (Math.abs(Encoders.getInstance().getLeftDistance() + Encoders.getInstance().getRightDistance()) / 2);

		if (error < Variables.getInstance().getDriveStraightTolerance()) {
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		_pid.disable();
		DriveTrain.getInstance().drive(0, 0);
		Encoders.getInstance().driveEncoderReset();
	}
}
