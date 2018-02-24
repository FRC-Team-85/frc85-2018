package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.Addresses;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU {

	private static IMU _instance;

	private TalonSRX _connectedTalon;
	private PigeonIMU _pigeon;

	private PigeonIMU.GeneralStatus _genStatus = new PigeonIMU.GeneralStatus();
	private double[] _ypr = new double[3];

	private IMU() {
		_connectedTalon = new TalonSRX(Addresses.IMU_TALON);
		_pigeon = new PigeonIMU(_connectedTalon);
	}

	public static IMU getInstance() {
		if (_instance == null) {
			_instance = new IMU();
		}
		return _instance;
	}

	public ErrorCode getStatus() {
		return _pigeon.getGeneralStatus(_genStatus);
	}

	public double[] getYPR() {
		ErrorCode error = _pigeon.getYawPitchRoll(_ypr);
		SmartDashboard.putString("IMU Error Code", error.toString());
		return _ypr;
	}

	public double getFusedHeading() {
		return _pigeon.getFusedHeading();
	}

	public PigeonIMU getIMU() {
		return _pigeon;
	}
}
