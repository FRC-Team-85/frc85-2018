package org.usfirst.frc.team85.robot;

import org.usfirst.frc.team85.robot.sensors.LimitSwitches;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.PWM;

public class Indicators {

	private static Indicators _instance;

	private PWM leftGreen, leftRed, rightGreen, rightRed;

	private int LG = 0, LR = 0, RG = 0, RR = 0;

	private Indicators() {
		leftGreen = new PWM(Addresses.INDICATORS_LEFT_GREEN);
		leftRed = new PWM(Addresses.INDICATORS_LEFT_RED);
		rightGreen = new PWM(Addresses.INDICATORS_RIGHT_GREEN);
		rightRed = new PWM(Addresses.INDICATORS_RIGHT_RED);
	}

	public static Indicators getInstance() {
		if (_instance == null) {
			_instance = new Indicators();
		}
		return _instance;
	}

	public void periodic() {
		if (Lift.getInstance().isLifted()) {
			LG = 0;
			LR = 255;
			RG = 0;
			RR = 255;
		} else {
			if (LimitSwitches.getInstance().getLeftIntakeLimit()) {
				LG = 255;
				LR = 0;
			} else {
				LG = 255;
				LR = 255;
			}
			if (LimitSwitches.getInstance().getRightIntakeLimit()) {
				RG = 255;
				RR = 0;
			} else {
				RG = 255;
				RR = 255;
			}
		}
		setValues();
	}

	public void setValues() {
		leftGreen.setRaw(LG);
		leftRed.setRaw(LR);
		rightGreen.setRaw(RG);
		rightRed.setRaw(RR);
	}
}
