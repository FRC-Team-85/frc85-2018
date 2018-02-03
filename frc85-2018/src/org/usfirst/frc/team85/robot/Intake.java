package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake {

	Globals globals = Globals.getInstance();

	private boolean isIntakeOpen;
	private double wheelSpeed; // optimal wheel speed determined by tests. later theoretical support for
								// smartdashboard

	public Intake() {

	}

	public void setIntakePosition(boolean open) {
		if (open) {
			globals.getLeftIntakeSolenoid().set(true);
			globals.getRightIntakeSolenoid().set(true);
			isIntakeOpen = true;
		} else {
			globals.getLeftIntakeSolenoid().set(false);
			globals.getRightIntakeSolenoid().set(false);
			isIntakeOpen = false;
		}
	}

	public boolean getIntakePosition() {
		return isIntakeOpen;
	}

	public void setIntakeWheels(boolean on) {
		wheelSpeed = 1; // only necessary right now
		if (on) {
			globals.getLeftIntakeWheel().set(ControlMode.PercentOutput, wheelSpeed);
			globals.getRightIntakeWheel().set(ControlMode.PercentOutput, wheelSpeed);
		} else {
			globals.getLeftIntakeWheel().set(ControlMode.PercentOutput, 0);
			globals.getRightIntakeWheel().set(ControlMode.PercentOutput, 0);
		}
	}

}
