package org.usfirst.frc.team85.robot.commands.drivetrain;

import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightTimer extends TimedCommand {

	private static final double kP = 0.1, kI = 0.000001, kD = 0.2;
	// .1,.000001,.2

	private PIDController _pid;

	private double _speed;

	public DriveStraightTimer(double speed, double time) {
		super(time);
		requires(DriveTrain.getInstance());
		_speed = speed;
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
	}

	@Override
	protected void end() {
		_pid.disable();
		DriveTrain.getInstance().drive(0, 0);
	}
}
