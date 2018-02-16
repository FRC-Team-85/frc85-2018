package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private Globals _globals;
	private Drive _drive;
	private Auto _auto;
	private Encoders _encoders;

	@Override
	public void robotInit() {
		_globals = Globals.getInstance();
		_drive = Drive.getInstance();
		_encoders.driveEncoderReset();
	}

	@Override
	public void autonomousInit() {
		String fieldKey = DriverStation.getInstance().getGameSpecificMessage();
		_auto = new Auto(fieldKey);
	}

	@Override
	public void autonomousPeriodic() {
		double[] temp = _auto.autoTick();

		_globals.getMotorGroupLeft().setPower(temp[0]);
		_globals.getMotorGroupRight().setPower(temp[1]);
	}

	@Override
	public void teleopPeriodic() {
		_drive.periodic();
		_encoders.getLeftVelocity();
		_encoders.getRightVelocity();
	}

	@Override
	public void testPeriodic() {

	}
	
	@Override
	public void disabledPeriodic() {
		//System.out.println("Range finder verify: " + _rangeFinder.verify());
	}
}
