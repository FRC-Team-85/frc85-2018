package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.Addresses;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU {

	private static IMU _instance;

	private TalonSRX _tempTalon;
	private PigeonIMU _pigeon;

	private PigeonIMU.GeneralStatus _genStatus = new PigeonIMU.GeneralStatus();
	private double[] _ypr = new double[3];

	private IMU() {
		_tempTalon = new TalonSRX(Addresses.IMU_TALON);
		_pigeon = new PigeonIMU(_tempTalon);
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

	public void show() {
		SmartDashboard.putNumber("AbsoluteCompassHeading", _pigeon.getAbsoluteCompassHeading());
		SmartDashboard.putNumber("CompassFieldStrength", _pigeon.getCompassFieldStrength());
		SmartDashboard.putNumber("CompassHeading", _pigeon.getCompassHeading());
		SmartDashboard.putNumber("FusedHeading", _pigeon.getFusedHeading());
		SmartDashboard.putNumber("Temp", _pigeon.getTemp());
		SmartDashboard.putNumber("FirmwareVersion", _pigeon.getFirmwareVersion());

		double[] q6 = new double[4];
		_pigeon.get6dQuaternion(q6);
		SmartDashboard.putString("6dQuaternion", q6[0] + " : " + q6[1] + " : " + q6[2] + " : " + q6[3]);

		double[] acc = new double[3];
		_pigeon.getAccelerometerAngles(acc);
		SmartDashboard.putString("AccelerometerAngles", acc[0] + " : " + acc[1] + " : " + acc[2]);

		double[] gyro = new double[3];
		_pigeon.getAccumGyro(gyro);
		SmartDashboard.putString("AccumGyro", gyro[0] + " : " + gyro[1] + " : " + gyro[2]);

		short[] rawmag = new short[3];
		_pigeon.getRawMagnetometer(rawmag);
		SmartDashboard.putString("RawMagnetometer", rawmag[0] + " : " + rawmag[1] + " : " + rawmag[2]);

		double[] rawgyro = new double[3];
		_pigeon.getRawGyro(rawgyro);
		SmartDashboard.putString("RawGyro", rawgyro[0] + " : " + rawgyro[1] + " : " + rawgyro[2]);

		double[] ypr = new double[3];
		_pigeon.getYawPitchRoll(ypr);
		SmartDashboard.putString("YawPitchRoll", ypr[0] + " : " + ypr[1] + " : " + ypr[2]);
	}
}
