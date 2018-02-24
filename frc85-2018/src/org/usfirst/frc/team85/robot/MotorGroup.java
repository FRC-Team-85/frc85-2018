package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MotorGroup {

	public static final double GEAR_RATIO = 6.64;

	private TalonSRX[] _motors;

	public MotorGroup(int[] a) {
		_motors = new TalonSRX[a.length];

		for (int i = 0; i < a.length; i++) {
			_motors[i] = new TalonSRX(a[i]);
			_motors[i].setNeutralMode(NeutralMode.Brake);
			_motors[i].configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		}
	}

	public void setPower(double power) {
		for (int i = 0; i < _motors.length; i++) {
			_motors[i].set(ControlMode.PercentOutput, power);
		}
	}
}
