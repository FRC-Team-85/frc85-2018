package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.Autonomous;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Gripper;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	Command _autonomous;

	public static DriveTrain driveTrain;
	public static Lift lift;
	public static Gripper gripper;

	@Override
	public void robotInit() {
		driveTrain = DriveTrain.getInstance();
		lift = Lift.getInstance();
		gripper = Gripper.getInstance();

		_autonomous = new Autonomous();

		// Output?
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(lift);
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
		// log to dashboard
	}
}
