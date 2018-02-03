package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	Globals global = Globals.getInstance();
	Auto auto;

	@Override
	public void robotInit() {
		global.init();
	}

	@Override
	public void autonomousInit() {
		String fieldKey = DriverStation.getInstance().getGameSpecificMessage();
		auto = new Auto(fieldKey);
	}

	@Override
	public void autonomousPeriodic() {
		double[] temp = auto.autoTick();

		global.mgLeft.setPower(temp[0]);
		global.mgRight.setPower(temp[1]);
	}

	@Override
	public void teleopPeriodic() {
		Drive.periodic();
		SmartDashboard.putNumber("RangeFinder", global.rangeFinder.getDistance());
	}

	@Override
	public void testPeriodic() {

	}
	
	@Override
	public void disabledPeriodic() {
		//System.out.println("Range finder verify: " + _rangeFinder.verify());
	}
}
