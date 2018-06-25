package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class SpinExactDegrees extends Command {

	// private static final double kP = 0.05, kI = 0.00001, kD = 0.1;
	private PIDController _pid;

	private double _targetAngle;
	private double _changeAngle;

	// left = positive, right = negative
	public SpinExactDegrees(double angle) {
		requires(DriveTrain.getInstance());
		_changeAngle = angle;
	}

	public SpinExactDegrees(double angle, double timeout) {
		requires(DriveTrain.getInstance());
		setTimeout(timeout);
		_changeAngle = angle;
	}

	@Override
	protected void initialize() {
		super.initialize();
		_targetAngle = _changeAngle + IMU.getInstance().getFusedHeading();

		_pid = new PIDController(Variables.getInstance().getSpinKP(), Variables.getInstance().getSpinKI(),
				Variables.getInstance().getSpinKD(), new PIDSource() {
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

		_pid.setSetpoint(_targetAngle);
		_pid.setAbsoluteTolerance(Variables.getInstance().getSpinTolerance());

		_pid.setOutputRange(-50, 50);
		_pid.reset();
		_pid.enable();
	}

	public void applyCorrection(double correction) {
		if (correction > 0) {
			DriveTrain.getInstance().drive(-Math.abs(correction) / 2, Math.abs(correction) / 2);
		} else {
			DriveTrain.getInstance().drive(Math.abs(correction) / 2, -Math.abs(correction) / 2);
		}
	}

	@Override
	protected boolean isFinished() {
		return _pid.onTarget() || isTimedOut();
	}

	@Override
	protected void end() {
		_pid.disable();
		DriveTrain.getInstance().drive(0, 0);
		Encoders.getInstance().driveEncoderReset();
	}
}