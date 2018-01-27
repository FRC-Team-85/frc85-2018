package org.usfirst.frc.team85.robot.auto;

public class PID {

	private double P = .5;
	private double I = .3569874456465314;
	private double D = 0;

	private double _target;
	private double _integral;
	private double _derivative;

	private double _error = 0;
	private double _prevError = 0;

	private long _changeTime = 0;
	private long _prevTime = 0;

	public PID(double target) {
		target = _target;
	}

	public void setTarget(double target) {
		this._target = target;
	}

	public double getCorrection(double value) {
		_error = _target - value;
		_changeTime = (System.currentTimeMillis() - _prevTime) / 1000;

		_integral += (_error * _changeTime);
		_derivative = (_error - _prevError) / _changeTime;

		_prevError = _error;
		_prevTime = System.currentTimeMillis();

		return P * _error + I * _integral + D * _derivative;
	}
}
