package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SuperStructure {

	@Override
	public void robotInit() {
		controller = new Controller(0);

		try {
			mgLeft = new MotorGroup(new int[] { Addresses.leftBackTalon, Addresses.leftFrontTalon });
			mgRight = new MotorGroup(new int[] { Addresses.rightBackTalon, Addresses.rightFrontTalon });
		} catch (Exception e) {
			mgLeft = new MotorGroup(new int[] { Addresses.leftBackTalon, Addresses.leftFrontTalon });
			mgRight = new MotorGroup(new int[] { Addresses.rightBackTalon, Addresses.rightFrontTalon });
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

		if (Math.abs(controller.getAxis(3)) >= .1) {
			speedRight = Math.pow(controller.getAxis(3), power);
		} else if ((Math.abs(controller.getAxis(3)) < .1) && (Math.abs(controller.getAxis(3)) >= .01)) {
			speedRight = .1;
		} else if (Math.abs(controller.getAxis(3)) < .01) {
			speedRight = 0;
		}

		if (Math.abs(controller.getAxis(1)) >= .1) {
			speedLeft = Math.pow(controller.getAxis(1), power);
		} else if ((Math.abs(controller.getAxis(1)) < .1) && (Math.abs(controller.getAxis(1)) >= .01)) {
			speedLeft = .1;
		} else if (Math.abs(controller.getAxis(1)) < .01) {
			speedLeft = 0;
		}

		if (controller.getX()) {
			power = 1;
		} else if (controller.getA()) {
			power = 2;
		} else if (controller.getB()) {
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
