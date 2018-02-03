package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU {

	private TalonSRX _tempTalon = new TalonSRX(Addresses.tempTalon);
	private PigeonIMU _pigeon = new PigeonIMU(_tempTalon);

	private PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();

	private double[] ypr = new double[3];

	public ErrorCode getStatus() {
		return _pigeon.getGeneralStatus(genStatus);
	}

	public double[] getYPR() {
		ErrorCode error = _pigeon.getYawPitchRoll(ypr);
		SmartDashboard.putString("IMU Error Code", error.toString());
		return ypr;
	}

	public double getCompassHeading() {
		// return _pigeon.getAbsoluteCompassHeading();
		return _pigeon.getCompassHeading();
	}
}
