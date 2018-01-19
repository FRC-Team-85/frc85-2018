package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	private Controller _driveStick;
	private Prototype pair1, pair2;

	@Override
	public void robotInit() {
		_driveStick = new Controller(0);
		
		SmartDashboard.putNumber("motor1", 1);
		SmartDashboard.putNumber("motor2", 2);
		
		pair1 = new Prototype(SmartDashboard.getNumber("motor1", 1), Controller.a, Controller.b, _driveStick);
		pair2 = new Prototype(SmartDashboard.getNumber("motor2", 2), Controller.x, Controller.y, _driveStick);
	}

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopPeriodic() {
		pair1.setMotors();
		pair2.setMotors();
		
	}

	@Override
	public void testPeriodic() {
	}
}
