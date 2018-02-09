package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.SpinDegrees;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

	private static OI _instance;

	private Joystick _leftJoystick;
	private Joystick _rightJoystick;

	private OI() {
		_leftJoystick = new Joystick(Addresses.LEFT_JOYSTICK);
		_rightJoystick = new Joystick(Addresses.RIGHT_JOYSTICK);

		JoystickButton spinLeft = new JoystickButton(_leftJoystick, 4);
		JoystickButton spinRight = new JoystickButton(_leftJoystick, 5);

		spinLeft.whenPressed(new SpinDegrees(90));
		spinRight.whenPressed(new SpinDegrees(-90));
	}

	public static OI getInstance() {
		if (_instance == null) {
			_instance = new OI();
		}
		return _instance;
	}

	public double[] getSpeedInput() {
		double speedRight = 0;
		double speedLeft = 0;
		double power = (double) SmartDashboard.getNumber("Power", 1);
		double multLAxis0 = (double) SmartDashboard.getNumber("Left Joystick Turning Multiplier", .5);
		double RAxis0 = (_rightJoystick.getRawAxis(0));
		double RAxis1 = (_rightJoystick.getRawAxis(1));
		double LAxis0 = (_leftJoystick.getRawAxis(0));
		double LAxis1 = (_leftJoystick.getRawAxis(1));
		// throttle
		if (_rightJoystick.getRawButton(1) && Math.abs(RAxis1) > .1) {
			speedLeft = (RAxis1);
			speedRight = (RAxis1);

			if (LAxis0 > .1) {

				if (RAxis1 > 0) {
					speedRight = RAxis1 - LAxis0 * multLAxis0;
				} else {
					speedRight = RAxis1 + LAxis0 * multLAxis0;
				}
			} else if (LAxis0 < -.1) {
				if (RAxis1 > 0) {
					speedLeft = RAxis1 + LAxis0 * multLAxis0;
				} else {
					speedLeft = RAxis1 - LAxis0 * multLAxis0;
				}
			}
			if (_leftJoystick.getRawButton(1)) {
				multLAxis0 = .50;
			} else {
				multLAxis0 = .25;
			}
		} else {
			if (RAxis1 > .09 && RAxis1 < .1) {
				double startTime = System.currentTimeMillis();
				double p1 = RAxis1;
				double startTime2 = System.currentTimeMillis();
				double p2 = RAxis1;
				double startTime3 = System.currentTimeMillis();
				double p3 = RAxis1;
				double Vel1 = (p2 - p1) / (startTime2 - startTime);
				double Vel2 = (p3 - p2) / (startTime3 - startTime2);
				if ((Vel2 / Vel1) > .9) {
					power = (0);
				}
				if ((Vel2 / Vel1) <= .9) {
					power = 3;
				}
			} else {
				power = 3;
			}

			if (Math.abs(RAxis1) >= .1) {
				speedRight = Math.pow(RAxis1, power);
			} else if (Math.abs(RAxis1) < .1) {
				speedRight = 0;
			}

			if (Math.abs(LAxis1) >= .1) {
				speedLeft = Math.pow(LAxis1, power);
			} else if (Math.abs(LAxis1) < .1) {
				speedLeft = 0;
			}

			if (_rightJoystick.getRawButton(8)) {
				power = 3;
			}
			if (RAxis0 < 0) {
				speedRight = (-speedRight);
			}
			if (LAxis0 < 0) {
				speedLeft = (-speedLeft);
			}
		}

		SmartDashboard.putNumber("Power", power);
		SmartDashboard.putNumber("Left Joystick Turning Multiplier", multLAxis0);
		SmartDashboard.putNumber("RangeFinder", Globals.getInstance().getRangeFinder().getDistance());

		return new double[] { -speedRight, -speedLeft };
	}

	public boolean isFPS() {
		return _rightJoystick.getRawButton(1);
	}
}