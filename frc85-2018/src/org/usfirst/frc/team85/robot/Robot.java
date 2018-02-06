package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	Globals global;
	Auto auto;

	@Override
	public void robotInit() {
		global = Globals.getInstance();

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
	
	@Override
	public void disabledPeriodic() {
		//System.out.println("Range finder verify: " + _rangeFinder.verify());
	}
}
