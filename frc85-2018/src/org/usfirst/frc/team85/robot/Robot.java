package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	private Controller _driveStick;
	
	private Prototype group1, group2;

	@Override
	public void robotInit() {
		_driveStick = new Controller(0);
		
		SmartDashboard.putNumber("motor1", 1);
		SmartDashboard.putNumber("motor2", 2);
		
		group1 = new Prototype(SmartDashboard.getNumber("motor1", 1), Controller.a, Controller.b, _driveStick);
		group2 = new Prototype(SmartDashboard.getNumber("motor2", 2), Controller.x, Controller.y, _driveStick);
	}

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopPeriodic() {
		group1.setButtonPairs();
		group2.setButtonPairs();
		
	}

	@Override
	public void testPeriodic() {
	}
}
