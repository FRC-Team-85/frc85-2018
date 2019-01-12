package org.usfirst.frc.team85.robot.sensors;

import org.usfirst.frc.team85.robot.subsystems.Lift;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU {

	private static IMU _instance;
	private PigeonIMU _pigeon;

	private PigeonIMU.GeneralStatus _genStatus = new PigeonIMU.GeneralStatus();
	private double[] _ypr = new double[3];
	private short[] _xyz = new short[3];

	private double _initialHeading = 0;
	private double[] _initialYPR = new double[3];

	private IMU() {
		_pigeon = new PigeonIMU(Lift.getInstance().getIMUTalon());
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

	public void setInitialYPR() {
		_initialYPR = getYPR();
	}

	public double[] getInitialYPR() {
		return _initialYPR;
	}

	public double getInitialYaw() {
		return _initialYPR[0];
	}

	public double getInitialPitch() {
		return _initialYPR[2];
	}

	public double getInitialRoll() {
		return _initialYPR[1];
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

	public double getYaw() {
		_ypr = getYPR();
		return _ypr[0] - _initialYPR[0];
	}

	public double getPitch() {
		_ypr = getYPR();
		return _ypr[2] - _initialYPR[2];
	}

	public double getRoll() {
		_ypr = getYPR();
		return _ypr[1] - _initialYPR[1];
	}

	public short[] getXYZ() { // Accelerometer
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

}
