package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;

public class MotorGroup {

	public static final double GEAR_RATIO = 6.64;

	TalonSRX[] motors;
	Encoder enc = null;

	public MotorGroup(int[] a, Encoder enc) {
		this.enc = enc;
		if (enc != null) {
			enc.setMaxPeriod(.1);
			enc.setMinRate(10);
			enc.setDistancePerPulse((3.14 * (4.0 / 12.0)) / 250.0 / GEAR_RATIO);
			enc.setReverseDirection(true);
			enc.setSamplesToAverage(7);
			enc.reset();
		}

		motors = new TalonSRX[a.length];

		for (int i = 0; i < a.length; i++) {
			motors[i] = new TalonSRX(a[i]);
			motors[i].setNeutralMode(NeutralMode.Brake);
			motors[i].configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 1);
		}
	}

	public void setPower(double power) {
		for (int i = 0; i < motors.length; i++) {
			motors[i].set(ControlMode.PercentOutput, power);
		}
	}

	public double getDistance() {
		if (enc != null) {
			return enc.getDistance();
		}
		return -1;
	}

	public double getSpeed() {
		return motors[0].getMotorOutputPercent();
	}
}
