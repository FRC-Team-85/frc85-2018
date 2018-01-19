package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	
	public static final int a = 2;
	public static final int b = 3;
	public static final int x = 1;
	public static final int y = 4;
	
	public static final int back = 9;
	public static final int start = 10;
	
	public static final int leftTrig = 7;
	public static final int rightTrig = 8;
	
	public static final int leftBump = 5;
	public static final int rightBump = 6;
	
	public static final int leftClick = 11;
	public static final int rightClick = 12;
	
	private Joystick _controller;
	
	public Controller(int id) {
		_controller = new Joystick(id);
	}
	
	public boolean getButton(int button) {
		return _controller.getRawButton(button);
	}

	public boolean getA() {
		return _controller.getRawButton(a);
	}
	
	public boolean getB() {
		return _controller.getRawButton(b);
	}
	
	public boolean getX() {
		return _controller.getRawButton(x);
	}
	
	public boolean getY() {
		return _controller.getRawButton(y);
	}
	
	public boolean getLeftTrig() {
		return _controller.getRawButton(leftTrig);
	}
	
	public boolean getRightTrig() {
		return _controller.getRawButton(rightTrig);
	}
	
	public boolean getLeftBump() {
		return _controller.getRawButton(leftBump);
	}
	
	public boolean getRightBump() {
		return _controller.getRawButton(rightBump);
	}
	
	public boolean getBack() {
		return _controller.getRawButton(back);
	}
	
	public boolean getStart() {
		return _controller.getRawButton(start);
	}
	
	public boolean getLeftClick() {
		return _controller.getRawButton(leftClick);
	}
	
	public boolean getRightClick() {
		return _controller.getRawButton(rightClick);
	}
}
