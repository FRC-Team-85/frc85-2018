package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.Autonomous;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.sensors.RangeFinder;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private Command _autonomous;

	@Override
	public void robotInit() {
		DriveTrain.getInstance();
		OI.getInstance();
		IMU.getInstance();
		RangeFinder.getInstance();

		_autonomous = new Autonomous();
		new DriverAssistCameras();
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
		IMU.getInstance().log();
		SmartDashboard.putNumber("UltraSonic Value", RangeFinder.getInstance().getDistance());
	}
}
