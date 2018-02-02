package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IMU {
	
	private TalonSRX _tempTalon = new TalonSRX(Addresses.tempTalon);
	private PigeonIMU _pigeon = new PigeonIMU(_tempTalon);
	
	private PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();
	
	private double[] ypr;
	
	public ErrorCode getStatus() {
		return _pigeon.getGeneralStatus(genStatus);
	}
	
	public ErrorCode getYPR() {
		return _pigeon.getYawPitchRoll(ypr);
	}
	
}
