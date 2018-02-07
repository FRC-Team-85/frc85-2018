package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MotorGroup {

	public static final double GEAR_RATIO = 6.64;

	private TalonSRX[] _motors;
	private long _count = 0;

	public MotorGroup(int[] a) {
		_motors = new TalonSRX[a.length];

		for (int i = 0; i < a.length; i++) {
			_motors[i] = new TalonSRX(a[i]);
			_motors[i].setNeutralMode(NeutralMode.Brake);
			_motors[i].configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		}

		resetCountVariable();
	}

	public void setPower(double power) {
		for (int i = 0; i < _motors.length; i++) {
			_motors[i].set(ControlMode.PercentOutput, power);
		}
	}

	public long getDistance() {
		// this method (and others) seem to only get data from the first motors
		// in array (which seem to be the back ones). is this ok?
		return _motors[0].getSelectedSensorPosition(0) - _count;
	}

	public double getPercentSpeed() {
		return _motors[0].getMotorOutputPercent();
	}

	public double getInstantaneousVel() {
		// there seem to be 3 ways to get velocity, we should investigate which
		// one to use
		return _motors[0].getSensorCollection().getQuadratureVelocity();
	}

	public void resetCountVariable() {
		_count = _motors[0].getSelectedSensorPosition(0);
	}
}
