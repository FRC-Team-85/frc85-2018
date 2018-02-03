package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IMU {
	
	private TalonSRX _tempTalon = new TalonSRX(Addresses.tempTalon);
	private PigeonIMU _pigeon = new PigeonIMU(_tempTalon);
	// Hey you guys followed the download 
	private PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();
	//_pigeon.GetGeneralStatus(genStatus);
	private double[] ypr;
	//_pigeon.GetYawPitchRoll(ypr);
	//System.out.println("Yaw:" + ypr[0]);
	
	public ErrorCode getStatus() {
		return _pigeon.getGeneralStatus(genStatus);
	}
	
	public ErrorCode getYPR() {
		return _pigeon.getYawPitchRoll(ypr);
	}
	
}
