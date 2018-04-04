package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.Autonomous;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.sensors.LimitSwitches;
import org.usfirst.frc.team85.robot.sensors.RangeFinder;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Gripper;
import org.usfirst.frc.team85.robot.subsystems.Intake;
import org.usfirst.frc.team85.robot.subsystems.Lift;
import org.usfirst.frc.team85.robot.vision.Vision;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private Autonomous _autonomous;
	private Diagnostics _diagnostics;

	@Override
	public void robotInit() {
		Globals.getInstance();

		DriveTrain.getInstance();
		Gripper.getInstance();
		Intake.getInstance();
		Lift.getInstance();

		Encoders.getInstance();
		IMU.getInstance();
		LimitSwitches.getInstance();
		RangeFinder.getInstance();
		Vision.getInstance();

		_diagnostics = new Diagnostics();

		Intake.getInstance().apply(false);
		Lift.getInstance().lock(false);

		IMU.getInstance().setInitialYPR();
	}

	@Override
	public void autonomousInit() {
		_autonomous = new Autonomous((int) SmartDashboard.getNumber("Autonomous Position Selector", 1),
				DriverStation.getInstance().getGameSpecificMessage(),
				(int) SmartDashboard.getNumber("Autonomous Wait Time", 0),
				SmartDashboard.getBoolean("Autonomous Prioritize Scale", false),
				SmartDashboard.getBoolean("Autonomous Auto Line", false),
				SmartDashboard.getBoolean("Autonomous Drag Race", false));
		_autonomous.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		Lift.getInstance().periodic();
		Variables.getInstance().outputVariables();
		Indicators.getInstance().periodic();
		_diagnostics.log();
	}

	@Override
	public void teleopInit() {
		super.teleopInit();
		if (_autonomous != null) {
			_autonomous.cancel();
		}
		Encoders.getInstance().driveEncoderReset();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		OI.getInstance().periodic();
		Lift.getInstance().periodic();
		Variables.getInstance().outputVariables();
		Indicators.getInstance().periodic();
		_diagnostics.log();
	}

	@Override
	public void testPeriodic() {

	}

	@Override
	public void disabledPeriodic() {
		_diagnostics.close();
		Lift.getInstance().setDesiredHeight(Lift.getInstance().getPosition());
		Encoders.getInstance().driveEncoderReset();
	}
}
