package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MotorGroup {

	public static final double GEAR_RATIO = 6.64;

	TalonSRX[] motors;

	public MotorGroup(int[] a) {
		motors = new TalonSRX[a.length];

		for (int i = 0; i < a.length; i++) {
			motors[i] = new TalonSRX(a[i]);
			motors[i].setNeutralMode(NeutralMode.Brake);
			motors[i].configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		}
	}

	public void setPower(double power) {
		for (int i = 0; i < motors.length; i++) {
			motors[i].set(ControlMode.PercentOutput, power);
		}
	}

	public double getDistance() {
		// return motors[0].getSelectedSensorPosition(0);
		return 0;
	}

	public double getSpeed() {
		return motors[0].getMotorOutputPercent();
	}
}
