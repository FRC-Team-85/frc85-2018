package org.usfirst.frc.team85.robot.subsystems;

import org.usfirst.frc.team85.robot.Addresses;
import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.sensors.LimitSwitches;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem {

	private static Lift _instance = null;

	private double _desiredHeight = 0;
	private double _overrideSpeed = 0;

	private TalonSRX _leftOne, _leftTwo, _rightOne, _rightTwo;
	private Solenoid _lock;

	private Lift() {
		_rightOne = new TalonSRX(Addresses.LIFT_RIGHT_ONE);
		_rightOne.setNeutralMode(NeutralMode.Brake);
		_rightOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 20);
		_rightOne.setSelectedSensorPosition(0, 0, 0);
		_rightOne.selectProfileSlot(0, 0);

		_rightTwo = new TalonSRX(Addresses.LIFT_RIGHT_TWO);
		_rightTwo.follow(_rightOne);
		_rightTwo.setNeutralMode(NeutralMode.Brake);

		_leftOne = new TalonSRX(Addresses.LIFT_LEFT_ONE);
		_leftOne.follow(_rightOne);
		_leftOne.setNeutralMode(NeutralMode.Brake);

		_leftTwo = new TalonSRX(Addresses.LIFT_LEFT_TWO);
		_leftTwo.follow(_rightOne);
		_leftTwo.setNeutralMode(NeutralMode.Brake);

		_lock = new Solenoid(Addresses.LIFT_LOCK);
	}

	public static Lift getInstance() {
		if (_instance == null) {
			_instance = new Lift();
		}
		return _instance;
	}

	@Override
	protected void initDefaultCommand() {

	}

	public double getPower() {
		return _rightOne.getMotorOutputPercent();
	}

	public void setDesiredHeight(double height) {
		_desiredHeight = height;
	}

	public double getDesiredHeight() {
		return _desiredHeight;
	}

	public void setOverrideSpeed(double speed) {
		_overrideSpeed = speed;
		_desiredHeight = getPosition();
	}

	public void periodic() {
		double speed = 0;

		if (_overrideSpeed != 0) {
			speed = _overrideSpeed;
		} else {
			double error = Math.abs(_desiredHeight - getPosition());
			SmartDashboard.putNumber("Lift Position Error", error);

			if (_desiredHeight > getPosition()) {
				speed = Variables.getInstance().getLiftUpSpeed();
				if (error <= Variables.getInstance().getLiftUpwardDecelMultiple()
						* Variables.getInstance().getLiftTolerance()) {
					speed *= .2;
				}
			} else {
				speed = Variables.getInstance().getLiftDownSpeed();
				if (error <= Variables.getInstance().getLiftDownwardDecelMultiple()
						* Variables.getInstance().getLiftTolerance()) {
					speed *= .2;
				}
			}

			if (error <= Variables.getInstance().getLiftTolerance()) {
				speed = 0;
			}
		}

		if ((LimitSwitches.getInstance().getUpperLiftLimit() && speed > 0)
				|| (LimitSwitches.getInstance().getLowerLiftLimit() && speed < 0)) {
			speed = 0;
		}

		if (_desiredHeight == -1) {
			speed = 0;
		}

		if (LimitSwitches.getInstance().getLowerLiftLimit()) {
			_rightOne.setSelectedSensorPosition(0, 0, 0);
		}

		_rightOne.set(ControlMode.PercentOutput, speed);
	}

	public double getPosition() {
		return -_rightOne.getSelectedSensorPosition(0);
	}

	public void lock(boolean lock) {
		if (lock != isLocked()) {
			Variables.getInstance().addSolenoidFire();
		}
		_lock.set(!lock);
	}

	public boolean isLocked() {
		return !_lock.get();
	}

	public boolean isLifted() {
		return (getPosition() > Variables.getInstance().getLiftTolerance());
	}
}
