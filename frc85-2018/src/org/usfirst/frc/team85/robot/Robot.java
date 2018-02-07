package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;
import org.usfirst.frc.team85.robot.auto.Diagnostics;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	Globals global;
	Auto auto;
	public static Diagnostics _diagnostics;

	@Override
	public void robotInit() {
		global = Globals.getInstance();
		_diagnostics = new Diagnostics();
		_diagnostics.init();

		SmartDashboard.putNumber("High Amplitude", .65);
		SmartDashboard.putNumber("Low Amplitude", .35);
	}

	@Override
	public void autonomousInit() {
		String fieldKey = DriverStation.getInstance().getGameSpecificMessage();
		auto = new Auto(fieldKey);
	}

	@Override
	public void autonomousPeriodic() {
		double[] temp = auto.autoTick();

		SmartDashboard.putString("Robot Reading", temp[0] + ":" + temp[1]);
		global.getMotorGroupLeft().setPower(temp[0]);
		global.getMotorGroupRight().setPower(temp[1]);
	}

	@Override
	public void teleopPeriodic() {
		Drive.periodic();
		// SmartDashboard.putNumber("RangeFinder",
		// global.getRangeFinder().getDistance());
	}

	@Override
	public void testPeriodic() {

	}
}
