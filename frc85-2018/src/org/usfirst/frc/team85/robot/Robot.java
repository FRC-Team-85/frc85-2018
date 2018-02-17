package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.Autonomous;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {

<<<<<<< HEAD
	private Command _autonomous;
	private Diagnostics _diagnostics;

	@Override
	public void robotInit() {
		Globals.getInstance();

		_diagnostics = new Diagnostics();
		_diagnostics.init();

		_autonomous = new Autonomous();
	}

	@Override
	public void autonomousInit() {
	String fieldKey = 	DriverStation.getInstance().getGameSpecificMessage();
		_autonomous.start();		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		super.teleopInit();
		_autonomous.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void testPeriodic() {

	}

	@Override
	public void disabledPeriodic() {

	}

	private void log() {
		_diagnostics.log();

		IMU.getInstance().show();
		DriveTrain.getInstance().show();
	}
}
