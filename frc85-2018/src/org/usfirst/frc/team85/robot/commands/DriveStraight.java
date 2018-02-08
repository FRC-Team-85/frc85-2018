/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Globals;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class DriveStraight extends Command {
	private PIDController m_pid;
	private double _speed = 0;

	public DriveStraight(double speed, double heading) {
		requires(Globals.getInstance().getDriveTrain());
		_speed = speed;

		m_pid = new PIDController(4, 0, 0, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			@Override
			public double pidGet() {
				return Globals.getInstance().getGyro().getAngle();
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

		m_pid.setAbsoluteTolerance(0.01);
		m_pid.setSetpoint(heading);
	}

	public void applyCorrection(double correction) {
		if (correction > 0) {
			Globals.getInstance().getDriveTrain().drive(_speed - Math.sin(Math.abs(correction)), _speed);
		} else {
			Globals.getInstance().getDriveTrain().drive(_speed, _speed - Math.sin(Math.abs(correction)));
		}
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		// Get everything in a safe starting state.
		// Robot.driveTrain.reset();
		m_pid.reset();
		m_pid.enable();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		// return m_pid.onTarget();
		// Ultrasonic sensor data
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// Stop PID and the wheels
		m_pid.disable();
		Globals.getInstance().getDriveTrain().drive(0, 0);
	}
}
