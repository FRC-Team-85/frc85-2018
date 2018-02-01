package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.auto.Auto;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SuperStructure {

	@Override
	public void robotInit() {
		leftJoystick = new Joystick(1);
		rightJoystick = new Joystick(0);

		mgLeft = new MotorGroup(new int[] { Addresses.leftBackTalon, Addresses.leftFrontTalon });
		mgRight = new MotorGroup(new int[] { Addresses.rightBackTalon, Addresses.rightFrontTalon });

		rangeFinder = new RangeFinder();

		try {
			gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
			gyro.setPIDSourceType(PIDSourceType.kDisplacement);
			gyro.calibrate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
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
		Drive.periodic();
		SmartDashboard.putNumber("RangeFinder", rangeFinder.getDistance());
	}

	@Override
	public void testPeriodic() {

	}
}
