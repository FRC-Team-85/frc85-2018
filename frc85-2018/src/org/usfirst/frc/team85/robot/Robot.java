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
		double speedRight = 0;
		double speedLeft = 0;
		double power = (double) SmartDashboard.getNumber("Power", 1);
		double amplitude = (double) SmartDashboard.getNumber("Amplitude", .5);

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

		/*
		 * if (Math.abs(_leftJoystick.getRawAxis(1)) <= .1) { speedLeft = 0; } else if
		 * (Math.abs(_leftJoystick.getRawAxis(1)) < .9) { speedLeft = 2.5 *
		 * _leftJoystick.getRawAxis(1) * _leftJoystick.getRawAxis(1) - 1.25 *
		 * _leftJoystick.getRawAxis(1) + .1; } else { speedLeft = 1; }
		 * 
		 * if (Math.abs(_rightJoystick.getRawAxis(1)) <= .1) { speedRight = 0; } else if
		 * (Math.abs(_rightJoystick.getRawAxis(1)) < .9) { speedRight = 2.5 *
		 * _rightJoystick.getRawAxis(1) * _rightJoystick.getRawAxis(1) - 1.25 *
		 * _rightJoystick.getRawAxis(1) + .1; } else { speedRight = 1; }
		 */

		if (_rightJoystick.getRawButton(7)) {
			power = 1;
		}
		if (_rightJoystick.getRawButton(8)) {
			power = 3;
		}
		if (_leftJoystick.getRawButton(1)) {
			amplitude = .50;
		} else {
			amplitude = .25;
		}

		if (_rightJoystick.getRawButton(1) && Math.abs(_rightJoystick.getRawAxis(1)) > .1) {
			speedLeft = (_rightJoystick.getRawAxis(1));
			speedRight = (_rightJoystick.getRawAxis(1));

			if (_leftJoystick.getRawAxis(0) > .1) {
				if (_rightJoystick.getRawAxis(1) > 0) {
					speedRight = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * amplitude;
				} else {
					speedRight = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * amplitude;
				}
			} else if (_leftJoystick.getRawAxis(0) < -.1) {
				if (_rightJoystick.getRawAxis(1) > 0) {
					speedLeft = _rightJoystick.getRawAxis(1) + _leftJoystick.getRawAxis(0) * amplitude;
				} else {
					speedLeft = _rightJoystick.getRawAxis(1) - _leftJoystick.getRawAxis(0) * amplitude;
				}
			}
		}

		mgRight.setPower(-speedRight);
		mgLeft.setPower(-speedLeft);

		SmartDashboard.putNumber("Power", power);
		SmartDashboard.putNumber("Amplitude", amplitude);
		SmartDashboard.putNumber("RangeFinder", rangeFinder.getDistance());
	}

	@Override
	public void testPeriodic() {

	}
}
