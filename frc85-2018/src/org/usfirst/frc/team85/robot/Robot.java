package org.usfirst.frc.team85.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private TalonSRX _motor0 = new TalonSRX(0);
	private TalonSRX _motor1 = new TalonSRX(1);
	private TalonSRX _motor2 = new TalonSRX(2);
	private TalonSRX _motor3 = new TalonSRX(3);
	
	private Relay _light = new Relay(0);
	
	private Joystick _controller = new Joystick(0);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		
		SmartDashboard.putNumber("/motors/motor0", 1);
		SmartDashboard.putNumber("/motors/motor1", 1);
		SmartDashboard.putNumber("/motors/motor2", 1);
		SmartDashboard.putNumber("/motors/motor3", 1);

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
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
		_motor0.set(ControlMode.PercentOutput, SmartDashboard.getNumber("/motors/motor0", 0));
		_motor1.set(ControlMode.PercentOutput, SmartDashboard.getNumber("/motors/motor1", 0));
		_motor2.set(ControlMode.PercentOutput, SmartDashboard.getNumber("/motors/motor2", 0));
		_motor3.set(ControlMode.PercentOutput, SmartDashboard.getNumber("/motors/motor3", 0));

		 if (_controller.getRawButton(1)) {
			_light.set(Relay.Value.kForward);
		 } else {
			_light.set(Relay.Value.kOff);
		 }
		
		while(_controller.getRawButton(1)) {
			_light.set(Relay.Value.kForward);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_light.set(Relay.Value.kOff);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

