package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {

	private Joystick _controller = new Joystick(0);

	MotorGroup motorGroupRight = new MotorGroup(new int[] { 0, 1 });
	MotorGroup motorGroupLeft = new MotorGroup(new int[] { 2, 3 });

	private double _speedRight = 0;
	private double _speedLeft = 0;

	@Override
	public void robotInit() {

	}

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopPeriodic() {
		_speedRight = _controller.getRawAxis(1);
		_speedLeft = _controller.getRawAxis(3);

		motorGroupRight.setPower(_speedRight);
		motorGroupLeft.setPower(_speedLeft);
	}

	@Override
	public void testPeriodic() {
	}
}
