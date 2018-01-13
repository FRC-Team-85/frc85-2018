package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
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

	// private double motorSpeed = 0;

	private double _motor1Speed = 0;
	private double _motor2Speed = 0;
	private double _motor3Speed = 0;
	private double _motor4Speed = 0;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();

		SmartDashboard.putNumber("/motors/axis/motor1Axis", 0);
		SmartDashboard.putNumber("/motors/axis/motor2Axis", 0);
		SmartDashboard.putNumber("/motors/axis/motor3Axis", 0);
		SmartDashboard.putNumber("/motors/axis/motor4Axis", 0);
		
		SmartDashboard.putNumber("/motors/axis/test/motor1Axis", 0);
		SmartDashboard.putNumber("/motors/axis/test/motor2Axis", 0);
		SmartDashboard.putNumber("/motors/axis/test/motor3Axis", 0);
		SmartDashboard.putNumber("/motors/axis/test/motor4Axis", 0);

		SmartDashboard.putNumber("/motors/motor1", 0);
		SmartDashboard.putNumber("/motors/motor2", 0);
		SmartDashboard.putNumber("/motors/motor3", 0);
		SmartDashboard.putNumber("/motors/motor4", 0);

		SmartDashboard.putNumber("/motors/current/motor1Current", 0);
		SmartDashboard.putNumber("/motors/current/motor2Current", 0);
		SmartDashboard.putNumber("/motors/current/motor3Current", 0);
		SmartDashboard.putNumber("/motors/current/motor4Current", 0);

		// _compressor.setClosedLoopControl(true);

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		_motor1Speed = -_controller.getRawAxis((int) SmartDashboard.getNumber("/motors/axis/motor1Axis", 0));
		_motor2Speed = -_controller.getRawAxis((int) SmartDashboard.getNumber("/motors/axis/motor2Axis", 0));
		_motor3Speed = -_controller.getRawAxis((int) SmartDashboard.getNumber("/motors/axis/motor3Axis", 0));
		_motor4Speed = -_controller.getRawAxis((int) SmartDashboard.getNumber("/motors/axis/motor4Axis", 0));
		
		SmartDashboard.putNumber("/motors/axis/test/motor1Axis", (int) SmartDashboard.getNumber("/motors/axis/motor1Axis", 0));
		SmartDashboard.putNumber("/motors/axis/test/motor2Axis", (int) SmartDashboard.getNumber("/motors/axis/motor2Axis", 0));
		SmartDashboard.putNumber("/motors/axis/test/motor3Axis", (int) SmartDashboard.getNumber("/motors/axis/motor3Axis", 0));
		SmartDashboard.putNumber("/motors/axis/test/motor4Axis", (int) SmartDashboard.getNumber("/motors/axis/motor4Axis", 0));

		_motor1.set(ControlMode.PercentOutput, _motor1Speed);
		_motor2.set(ControlMode.PercentOutput, _motor2Speed);
		_motor3.set(ControlMode.PercentOutput, _motor3Speed);
		_motor4.set(ControlMode.PercentOutput, _motor4Speed);

		// SmartDashboard.putNumber("/motors/speed", motorSpeed);
		SmartDashboard.putNumber("/motors/motor1", _motor1Speed);
		SmartDashboard.putNumber("/motors/motor2", _motor2Speed);
		SmartDashboard.putNumber("/motors/motor3", _motor3Speed);
		SmartDashboard.putNumber("/motors/motor4", _motor4Speed);

		SmartDashboard.putNumber("/motors/current/motor1Current", _motor1.getOutputCurrent());
		SmartDashboard.putNumber("/motors/current/motor2Current", _motor2.getOutputCurrent());
		SmartDashboard.putNumber("/motors/current/motor3Current", _motor3.getOutputCurrent());
		SmartDashboard.putNumber("/motors/current/motor4Current", _motor4.getOutputCurrent());

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

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
