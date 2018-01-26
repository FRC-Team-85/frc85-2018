package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SuperStructure {

	@Override
	public void robotInit() {
		controller = new Joystick(0);

		try {
			mgLeft = new MotorGroup(new int[] { 1, 2 }, new Encoder(0, 1));
			mgRight = new MotorGroup(new int[] { 3, 4 }, new Encoder(0, 1));
		} catch (Exception e) {
			mgLeft = new MotorGroup(new int[] { 1, 2 }, null);
			mgRight = new MotorGroup(new int[] { 3, 4 }, null);
		}

		try {
			gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
			gyro.setPIDSourceType(PIDSourceType.kDisplacement);
			gyro.calibrate();
		} catch (Exception e) {
			System.err.println("Gyro instantiation failed");
		}
	}

	@Override
	public void autonomousInit() {
		auto = new Auto("LRL");
	}

	@Override
	public void autonomousPeriodic() {
		double[] temp = auto.autoTick();

		mgLeft.setPower(temp[0]);
		mgRight.setPower(temp[1]);
	}

	@Override
	public void teleopPeriodic() {
		double speedRight = 0;
		double speedLeft = 0;
		int power = (int) SmartDashboard.getNumber("Power", 1);

		if (Math.abs(controller.getRawAxis(3)) >= .1) {
			speedRight = Math.pow(controller.getRawAxis(3), power);
		} else if ((Math.abs(controller.getRawAxis(3)) < .1) && (Math.abs(controller.getRawAxis(3)) >= .01)) {
			speedRight = .1;
		} else if (Math.abs(controller.getRawAxis(3)) < .01) {
			speedRight = 0;
		}

		if (Math.abs(controller.getRawAxis(1)) >= .1) {
			speedLeft = Math.pow(controller.getRawAxis(1), power);
		} else if ((Math.abs(controller.getRawAxis(1)) < .1) && (Math.abs(controller.getRawAxis(1)) >= .01)) {
			speedLeft = .1;
		} else if (Math.abs(controller.getRawAxis(1)) < .01) {
			speedLeft = 0;
		}

		if (controller.getRawButton(1)) {
			power = 1;
		} else if (controller.getRawButton(2)) {
			power = 2;
		} else if (controller.getRawButton(3)) {
			power = 3;
		}
		mgRight.setPower(speedRight);
		mgLeft.setPower(speedLeft);

		SmartDashboard.putNumber("Power", power);
	}

	@Override
	public void testPeriodic() {

	}
}
