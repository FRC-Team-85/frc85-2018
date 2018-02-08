package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.commands.OpenGripper;
import org.usfirst.frc.team85.robot.commands.SpinDegrees;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	private static OI _instance;

	private Joystick _leftJoystick;
	private Joystick _rightJoystick;

	private OI() {
		_leftJoystick = new Joystick(Addresses.LEFT_JOYSTICK);
		_rightJoystick = new Joystick(Addresses.RIGHT_JOYSTICK);

		JoystickButton rightTrigger = new JoystickButton(_rightJoystick, 1);
		JoystickButton leftTrigger = new JoystickButton(_leftJoystick, 1);

		rightTrigger.whenPressed(new SpinDegrees(90));
		leftTrigger.whenPressed(new OpenGripper());
	}

	public static OI getInstance() {
		if (_instance == null) {
			_instance = new OI();
		}
		return _instance;
	}

	public double[] getSpeedInput() {
		double[] output = new double[] { 0, 0 };

		// traditional drive train code

		return output;
	}
}
