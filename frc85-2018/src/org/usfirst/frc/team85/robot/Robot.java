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
		_leftJoystick = new Joystick(1);
		_rightJoystick = new Joystick(0);

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
		double speedRight = 0;
		double speedLeft = 0;
		int power = (int) SmartDashboard.getNumber("Power", 1);
		if (Math.abs(_rightJoystick.getRawAxis(1)) >= .1) {
			speedRight = Math.pow(_rightJoystick.getRawAxis(1), power);
		} else if (Math.abs(_rightJoystick.getRawAxis(1)) < .1) {
			speedRight = 0;
		}

		if (Math.abs(_leftJoystick.getRawAxis(1)) >= .1) {
			speedLeft = Math.pow(_leftJoystick.getRawAxis(1), power);
		} else if (Math.abs(_leftJoystick.getRawAxis(1)) < .1) {
			speedLeft = 0;
		}

		if (_rightJoystick.getRawButton(7)) {
			power = 1;
		}
		if (_rightJoystick.getRawButton(8)) {
			power = 3;
		}

		if (_rightJoystick.getRawButton(1) && Math.abs(_rightJoystick.getRawAxis(1)) > .1) {
			speedLeft = (_rightJoystick.getRawAxis(1));
			speedRight = (_rightJoystick.getRawAxis(1));

			if (_leftJoystick.getRawAxis(0) > .1) {
				speedLeft = (_rightJoystick.getRawAxis(1) - .3);
			} else if (_leftJoystick.getRawAxis(0) < -.1) {
				speedRight = (_rightJoystick.getRawAxis(1) - .3);
			}
		}

		mgRight.setPower(-speedRight);
		mgLeft.setPower(-speedLeft);

		SmartDashboard.putNumber("Power", power);
	}

	@Override
	public void testPeriodic() {

	}
}
