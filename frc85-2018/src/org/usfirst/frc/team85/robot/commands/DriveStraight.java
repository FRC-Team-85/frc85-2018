/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	private PIDController m_pid;
	private double _speed = 0;

	public DriveStraight(double speed, double heading) {
		requires(DriveTrain.getInstance());
		_speed = speed;

		m_pid = new PIDController(4, 0, 0, new PIDSource() {
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

		m_pid.setAbsoluteTolerance(0.01);
		m_pid.setSetpoint(heading);
	}

	public void applyCorrection(double correction) {
		if (correction > 0) {
			DriveTrain.getInstance().drive(_speed - Math.sin(Math.abs(correction)), _speed);
		} else {
			DriveTrain.getInstance().drive(_speed, _speed - Math.sin(Math.abs(correction)));
		}
	}

	@Override
	protected void initialize() {
		m_pid.reset();
		m_pid.enable();
	}

	@Override
	protected boolean isFinished() {
		// Ultrasonic sensor data
		return false;
	}

	@Override
	protected void end() {
		m_pid.disable();
		DriveTrain.getInstance().drive(0, 0);
	}
}
