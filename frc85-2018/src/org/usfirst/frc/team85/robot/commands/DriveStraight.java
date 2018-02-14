package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends Command {

	private static final double kP = 0.1, kI = 0.000001, kD = 0.2;
	// .1,.000001,.2

	/*
	 * 
	 * Tuning Methods Zeigler-Nichols Zeigler-Nichols tuning method works by
	 * increasing P until the system starts oscillating, and then using the period
	 * of the oscillation to calculate I and D.
	 * 
	 * Start by setting I and D to 0. Increase P until the system starts oscillating
	 * for a period of Tu. You want the oscillation to be large enough that you can
	 * time it. This maximum P will be referred to as Ku. Use the chart below to
	 * calculate different P, I, and D values. Control Types P = 0.50*Ku PI =
	 * 0.45*Ku 0.54*Ku/Tu PID = 0.60*Ku 1.20*Ku/Tu 3*Ku*Tu/40
	 */

	private PIDController _pid;

	private double _speed;
	private double _distance;

	public DriveStraight(double speed, double distance) {
		requires(DriveTrain.getInstance());
		_speed = speed;
		_distance = distance;

		SmartDashboard.putNumber("PID/kp", kP);
		SmartDashboard.putNumber("PID/ki", kI);
		SmartDashboard.putNumber("PID/kd", kD);
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
		if (correction > 0) {
			DriveTrain.getInstance().drive(_speed - Math.abs(correction), _speed);
		} else {
			DriveTrain.getInstance().drive(_speed, _speed - Math.abs(correction));
		}

		_pid.setP(SmartDashboard.getNumber("PID/kp", kP));
		_pid.setI(SmartDashboard.getNumber("PID/ki", kI));
		_pid.setD(SmartDashboard.getNumber("PID/kd", kD));
	}

	@Override
	protected boolean isFinished() {
		// return (RangeFinder.getInstance().getDistance() <= _distance);
		return false;
	}

	@Override
	protected void end() {
		_pid.disable();
		DriveTrain.getInstance().drive(0, 0);
	}
}
