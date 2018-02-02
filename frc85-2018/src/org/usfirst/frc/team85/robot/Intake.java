package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {
	
	Globals globals = Globals.getInstance();
	
	private boolean isIntakeOpen;
	private double wheelSpeed; // optimal wheel speed determined by tests.  later theoretical support for smartdashboard
	
	public Intake() {
		globals.leftIntakeSolenoid = new Solenoid(Addresses.leftIntakeSolenoid);
		globals.rightIntakeSolenoid = new Solenoid(Addresses.rightIntakeSolenoid);
		
		globals.leftIntakeWheel = new TalonSRX(Addresses.leftIntakeTalon);
		globals.rightIntakeWheel = new TalonSRX(Addresses.rightIntakeTalon);
	}
	
	public void setIntakePosition(boolean open) {
		if (open) {
			globals.leftIntakeSolenoid.set(true);
			globals.rightIntakeSolenoid.set(true);
			isIntakeOpen = true;
		} else {
			globals.leftIntakeSolenoid.set(false);
			globals.rightIntakeSolenoid.set(false);
			isIntakeOpen = false;
		}
	}
	
	public boolean getIntakePosition() {
		return isIntakeOpen;
	}
	
	public void setIntakeWheels(boolean on) {
		wheelSpeed = 1; // only necessary right now
		if (on) {
			globals.leftIntakeWheel.set(ControlMode.PercentOutput, wheelSpeed);
			globals.rightIntakeWheel.set(ControlMode.PercentOutput, wheelSpeed);
		} else {
			globals.leftIntakeWheel.set(ControlMode.PercentOutput, 0);
			globals.rightIntakeWheel.set(ControlMode.PercentOutput, 0);
		}
	}
	
}
