package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OPBoard {
	
	private Joystick _opStick = new Joystick(1);
	
	public double getLiftStick() {
		return _opStick.getRawAxis(0);
	}
	
	public boolean getZeroInchLiftButton() {
		return _opStick.getRawButton(0);
	}
	
	public boolean getTwoInchLiftButton() {
		return _opStick.getRawButton(1);
	}
	
	public boolean getEighteenInchLiftButton() {
		return _opStick.getRawButton(2);
	}
	
	public boolean getFiftyOneInchLiftButton() {
		return _opStick.getRawButton(3);
	}
	
	public boolean getSeventyFiveInchLiftButton() {
		return _opStick.getRawButton(4);
	}
	
	public boolean getNinetySixInchLiftButton() {
		return _opStick.getRawButton(5);
	}
}
