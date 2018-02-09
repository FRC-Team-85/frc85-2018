package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {

	Command _autonomous;

	@Override
	public void robotInit() {
		Globals.getInstance();
		_autonomous = new Autonomous();
	}

	@Override
	public void autonomousInit() {
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

	}
}
