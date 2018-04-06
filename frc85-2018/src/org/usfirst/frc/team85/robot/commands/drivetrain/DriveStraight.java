package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.Vision;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.sensors.RangeFinder;
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

	private boolean _accel = true;
	private boolean _decel = true;
	private boolean _autoShift = false;
	private boolean _visionTrack = false;
	private boolean _useRangeFinders = false;
	private AbsoluteDirection _rangeDirection;

	public DriveStraight(double speed, double distance) {
		requires(DriveTrain.getInstance());
		Encoders.getInstance().driveEncoderReset();
		_speed = speed;
		_distance = Math.abs(distance);
	}

	public DriveStraight(double speed, double distance, double timeout) {
		requires(DriveTrain.getInstance());
		Encoders.getInstance().driveEncoderReset();
		setTimeout(timeout);
		_speed = speed;
		_distance = Math.abs(distance);
	}

	public DriveStraight setAbsoluteDirection(AbsoluteDirection direction) {
		_direction = direction;
		return this;
	}

	public DriveStraight setAcceleration(boolean accel, boolean decel) {
		_accel = accel;
		_decel = decel;
		return this;
	}

	public DriveStraight setAutoShift() {
		_autoShift = true;
		return this;
	}

	public DriveStraight setVisionTrack() {
		_visionTrack = true;
		return this;
	}

	public DriveStraight setRangeFinderDistance(AbsoluteDirection rangeDirection) {
		_useRangeFinders = true;
		_rangeDirection = rangeDirection;
		return this;
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

		// Absolute Direction Math
		if (_direction != null) {
			double currentHeading = IMU.getInstance().getFusedHeading();
			double initHeading = IMU.getInstance().getInitialHeading();
			int diff = (int) (currentHeading - initHeading);
			int rot = diff / 360;
			int remainder = diff % 360;

			switch (_direction) {
			case BACKWARD:
				if (remainder > 180) {
					_heading = initHeading + (rot * 360) + 540;
				} else {
					_heading = initHeading + (rot * 360) + 180;
				}
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
		} else {
			_heading = IMU.getInstance().getFusedHeading();
		}

		_pid.setSetpoint(_heading);
		_pid.setAbsoluteTolerance(2);

		_pid.setOutputRange(-10, 10);

		_pid.reset();
		_pid.enable();

		if (_visionTrack) {
			Vision.getInstance().setCommand(this);
		}
	}

	public void applyCorrection(double correction) {
		double error = getError();
		SmartDashboard.putNumber("DriveTrain Target Error", error);

		// Acceleration and Deceleration zones and speeds
		double applySpeed = _speed;
		if (error < Variables.getInstance().getDriveStraightDecelDistance() && _decel) {
			applySpeed *= (error / Variables.getInstance().getDriveStraightDecelDistance())
					* (1 - Variables.getInstance().getUsefulDriveTrainPower());
			applySpeed += Variables.getInstance().getUsefulDriveTrainPower() * (Math.abs(_speed) / _speed);
		} else if (error > _distance - Variables.getInstance().getDriveStraightAccelDistance() && _accel) {
			applySpeed *= (error / Variables.getInstance().getDriveStraightAccelDistance())
					* (1 - Variables.getInstance().getUsefulDriveTrainPower());
			applySpeed += Variables.getInstance().getUsefulDriveTrainPower() * (Math.abs(_speed) / _speed);
		}

		if (applySpeed > 1) {
			applySpeed = 1;
		}
		if (applySpeed < -1) {
			applySpeed = -1;
		}

		// Automatic Shifting based on speed and deceleration zone
		if (_autoShift) {
			if ((error < Variables.getInstance().getDriveStraightDecelDistance()) && _decel) {
				DriveTrain.getInstance().setHighGear(false);
			} else if ((Math.abs(Encoders.getInstance().getLeftVelocity()) > Variables.getInstance()
					.getDriveTrainHighGearThreshold())
					&& (Math.abs(Encoders.getInstance().getRightVelocity()) > Variables.getInstance()
							.getDriveTrainHighGearThreshold())) {
				DriveTrain.getInstance().setHighGear(true);
			}
		}

		// Applying PID correction Values
		if (correction > 0) {
			DriveTrain.getInstance().drive(applySpeed - Math.abs(correction), applySpeed);
		} else {
			DriveTrain.getInstance().drive(applySpeed, applySpeed - Math.abs(correction));
		}
	}

	@Override
	protected boolean isFinished() {
		if ((getError() < Variables.getInstance().getDriveStraightTolerance()) || isTimedOut()) {
			return true;
		}

		return false;
	}

	private double getError() {
		double range = 0;
		if (!_useRangeFinders) {
			range = (Math.abs(Encoders.getInstance().getLeftDistance() + Encoders.getInstance().getRightDistance())
					/ 2);
		} else {
			switch (_rangeDirection) {
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
		}
		return Math.abs(_distance - range);
	}

	@Override
	protected void end() {
		_pid.disable();
		DriveTrain.getInstance().drive(0, 0);
		Encoders.getInstance().driveEncoderReset();
		Vision.getInstance().setCommand(null);
	}

	public void setAngle(double angle) {
		_pid.setSetpoint(IMU.getInstance().getFusedHeading() + angle);
	}
}
