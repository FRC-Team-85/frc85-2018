package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {
	
	private Solenoid _leftIntakeSolenoid, _rightIntakeSolenoid;
	
	private TalonSRX _leftIntakeWheel, _rightIntakeWheel;
	
	private boolean isIntakeOpen;
	private double wheelSpeed; // optimal wheel speed determined by tests.  later theoretical support for smartdashboard
	
	public void IntakeThings() {
		_leftIntakeSolenoid = new Solenoid(Addresses.leftIntakeSolenoid);
		_rightIntakeSolenoid = new Solenoid(Addresses.rightIntakeSolenoid);
		
		_leftIntakeWheel = new TalonSRX(Addresses.leftIntakeTalon);
		_rightIntakeWheel = new TalonSRX(Addresses.rightIntakeTalon);
	}
	
	public void intakeControl(boolean open) {
		if (open) {
			_leftIntakeSolenoid.set(true);
			_rightIntakeSolenoid.set(true);
			isIntakeOpen = true;
		} else {
			_leftIntakeSolenoid.set(false);
			_rightIntakeSolenoid.set(false);
			isIntakeOpen = false;
		}
	}
	
	public boolean getIntakePosition() {
		return isIntakeOpen;
	}
	
	public void runIntakeWheels(boolean on) {
		wheelSpeed = 1; // only necessary right now
		if (on) {
			_leftIntakeWheel.set(ControlMode.PercentOutput, wheelSpeed);
			_rightIntakeWheel.set(ControlMode.PercentOutput, wheelSpeed);
		} else {
			_leftIntakeWheel.set(ControlMode.PercentOutput, 0);
			_rightIntakeWheel.set(ControlMode.PercentOutput, 0);
		}
	}
	
}
