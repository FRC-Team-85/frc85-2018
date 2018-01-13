package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MotorGroup {

	TalonSRX[] motors;

	public MotorGroup(int[] a) {
		motors = new TalonSRX[a.length];

		for (int i = 0; i < a.length; i++) {
			motors[i] = new TalonSRX(a[i]);
		}
	}

	public void setPower(double power) {
		for (int i = 0; i < motors.length; i++) {
			motors[i].set(ControlMode.PercentOutput, power);
		}
	}
}
