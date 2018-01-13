package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private TalonSRX _motor1 = new TalonSRX(1);
	private TalonSRX _motor2 = new TalonSRX(2);
	private TalonSRX _motor3 = new TalonSRX(3);
	private TalonSRX _motor4 = new TalonSRX(4);

	// private Relay _light = new Relay(0);
	/*
	 * private Compressor _compressor = new Compressor(0); private Solenoid
	 * _solenoid1 = new Solenoid(0, 1); private Solenoid _solenoid2 = new
	 * Solenoid(0, 3);
	 */

	private Joystick _controller = new Joystick(0);

	private double _motor1Speed = 0;
	private double _motor2Speed = 0;
	private double _motor3Speed = 0;
	private double _motor4Speed = 0;

	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();

		SmartDashboard.putNumber("/motors/motor1", 0);
		SmartDashboard.putNumber("/motors/motor2", 0);
		SmartDashboard.putNumber("/motors/motor3", 0);
		SmartDashboard.putNumber("/motors/motor4", 0);

		SmartDashboard.putNumber("/motors/current/motor1Current", 0);
		SmartDashboard.putNumber("/motors/current/motor2Current", 0);
		SmartDashboard.putNumber("/motors/current/motor3Current", 0);
		SmartDashboard.putNumber("/motors/current/motor4Current", 0);

		// _compressor.setClosedLoopControl(true);

		// MotorGroup leftDriveTrain = new MotorGroup(new int[] { 0, 1, 2, 3 });
		// logs.add(leftDriveTrain);
	}

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopPeriodic() {
		_motor1Speed = -_controller.getRawAxis(1);
		_motor2Speed = -_controller.getRawAxis(1);
		_motor3Speed = -_controller.getRawAxis(3);
		_motor4Speed = -_controller.getRawAxis(3);

		_motor1.set(ControlMode.PercentOutput, _motor1Speed);
		_motor2.set(ControlMode.PercentOutput, _motor2Speed);
		_motor3.set(ControlMode.PercentOutput, _motor3Speed);
		_motor4.set(ControlMode.PercentOutput, _motor4Speed);

		SmartDashboard.putNumber("/motors/motor1", _motor1Speed);
		SmartDashboard.putNumber("/motors/motor2", _motor2Speed);
		SmartDashboard.putNumber("/motors/motor3", _motor3Speed);
		SmartDashboard.putNumber("/motors/motor4", _motor4Speed);

		/*
		 * if(_controller.getRawButton(1)) { motorSpeed += 0.01; }
		 * 
		 * if(_controller.getRawButton(2)) { motorSpeed -= 0.01; }
		 */

		/*
		 * // B controls solenoid1 if (_controller.getRawButton(3)) {
		 * _solenoid1.set(true); } else { _solenoid1.set(false); }
		 * 
		 * // Y controls solenoid2 if (_controller.getRawButton(4)) {
		 * _solenoid2.set(true); } else { _solenoid2.set(false); }
		 */

		/*
		 * if (_controller.getRawButton(1)) { _light.set(Relay.Value.kForward); } else {
		 * _light.set(Relay.Value.kOff); }
		 * 
		 * while(_controller.getRawButton(1)) { _light.set(Relay.Value.kForward); try {
		 * Thread.sleep(500); } catch (InterruptedException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } _light.set(Relay.Value.kOff); try {
		 * Thread.sleep(500); } catch (InterruptedException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
	}

	@Override
	public void testPeriodic() {
	}
}
