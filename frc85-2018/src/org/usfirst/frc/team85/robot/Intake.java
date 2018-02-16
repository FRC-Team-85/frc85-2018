package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;

public class Intake {

	Globals globals = Globals.getInstance();

	private boolean isIntakeOpen;
	private boolean isGripperClosed;
	DigitalInput _leftCubeLimit, _rightCubeLimit;

	public Intake() {

	}

	public void setIntakePosition(boolean open) {
		globals.getLeftIntakeSolenoid().set(open);
		globals.getRightIntakeSolenoid().set(open);
		isIntakeOpen = open;
	}
	
	public void ExtendIntake(boolean extended) {
		globals.getLeftGripperSolenoid().set(extended);
		globals.getRightGripperSolenoid().set(extended);
		isGripperClosed = extended;
	}

	public boolean getIntakePosition() {
		return isIntakeOpen;
	}

	public void setIntakeWheels(boolean on, int wheelSpeed) {
		if (on) {
			if (!_leftCubeLimit.get()) {
				globals.getLeftIntakeWheel().set(ControlMode.PercentOutput, wheelSpeed);
			} else {
				globals.getLeftIntakeWheel().set(ControlMode.PercentOutput, 0);
			}
			
			if (!_rightCubeLimit.get()) {
				globals.getRightIntakeWheel().set(ControlMode.PercentOutput, wheelSpeed);
			} else {
				globals.getRightIntakeWheel().set(ControlMode.PercentOutput, 0);
			}
		} else {
			globals.getLeftIntakeWheel().set(ControlMode.PercentOutput, 0);
			globals.getRightIntakeWheel().set(ControlMode.PercentOutput, 0);
		}
	}

}
