package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.Autonomous;
import org.usfirst.frc.team85.robot.subsystems.Intake;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {

	private Autonomous _autonomous;
	private Diagnostics _diagnostics;

	@Override
	public void robotInit() {
		Globals.getInstance();

		_diagnostics = new Diagnostics();
		_diagnostics.init();

		_autonomous = new Autonomous();

		Intake.getInstance().apply(false);
		Lift.getInstance().lock(false);
	}

	@Override
	public void autonomousInit() {
		_autonomous.init(DriverStation.getInstance().getGameSpecificMessage());
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		Lift.getInstance().periodic();
		Variables.getInstance().outputVariables();
		_diagnostics.log();
	}

	@Override
	public void teleopInit() {
		super.teleopInit();
		_autonomous.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		OI.getInstance().periodic();
		Lift.getInstance().periodic();
		Variables.getInstance().outputVariables();
		_diagnostics.log();
		_diagnostics.solenoidLog();
	}

	@Override
	public void testPeriodic() {

	}

	@Override
	public void disabledPeriodic() {

	}

	@Override
	public void disabledInit() {
		super.disabledInit();
		Lift.getInstance().setDesiredHeight(-1);
	}
}
