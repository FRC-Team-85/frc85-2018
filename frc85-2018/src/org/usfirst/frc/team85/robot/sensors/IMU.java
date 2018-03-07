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
	
	private short[] _xyz = new short[3];

	private double _initialHeading = 0;

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
	
	public double getYaw() {
		_ypr = getYPR();
		return _ypr[0];
	}
	
	public double getPitch() {
		_ypr = getYPR();
		return _ypr[1];
	}
	
	public double getRoll() {
		_ypr = getYPR();
		return _ypr[2];
	}
	
	public short[] getXYZ() { //Accelerometer
		ErrorCode error = _pigeon.getBiasedAccelerometer(_xyz);
		SmartDashboard.putString("IMU Error Code", error.toString());
		return _xyz;
	}
	
	public double getX() {
		_xyz = getXYZ();
		return _xyz[0];
	}
	
	public double getY() {
		_xyz = getXYZ();
		return _xyz[1];
	}
	
	public double getZ() {
		_xyz = getXYZ();
		return _xyz[2];
	}

	public double getFusedHeading() {
		return _pigeon.getFusedHeading();
	}

	public PigeonIMU getIMU() {
		return _pigeon;
	}

	public void setInitialHeading() {
		_initialHeading = getFusedHeading();
	}

	public double getInitialHeading() {
		return _initialHeading;
	}
}
