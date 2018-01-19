package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Prototype {
	
	private TalonSRX _talon;
	private Controller _stick;
	private int _speed;
	private int _id;
	private int _button1;
	private int _button2;
	
	public Prototype(double id, int button1, int button2, Controller _controller) {
		_talon = new TalonSRX(_id);
		_button1 = button1;
		_button2 = button2;
		_stick = _controller;
	}
	
	public void setButtonPairs() {
		_talon.set(ControlMode.PercentOutput, _speed);
		
		if(_stick.getButton(_button1)) {
			_speed++;
		}
		else if(_stick.getButton(_button2)) {
			_speed--;
		}
	}

}
