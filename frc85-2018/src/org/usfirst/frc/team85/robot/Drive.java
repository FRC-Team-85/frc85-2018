/*package org.usfirst.frc.team85.robot;

public class Drive {

	protected static Drive instance = null;

	private MotorGroup _mgLeft = Globals.getInstance().getMotorGroupLeft();
	private MotorGroup _mgRight = Globals.getInstance().getMotorGroupRight();

	private Controller controller = Globals.getInstance().getController();

	private double _speedRight = 0;
	private double _speedLeft = 0;
	private double _turningAmplitude = .65;

	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}

	public void periodic() {

		if (Math.abs(controller.getRawAxis(5)) >= .1) {
			_speedRight = controller.getRawAxis(5);
		} else if (Math.abs(controller.getRawAxis(5)) < .1) {
			_speedRight = 0;
		}

		if (Math.abs(controller.getRawAxis(1)) >= .1) {
			_speedLeft = controller.getRawAxis(1) ;
		} else if (Math.abs(controller.getRawAxis(1)) < .1) {
			_speedLeft = 0;
		}
		if (controller.getRawAxis(3)> .3) {
		fpsDrive();
		}
		_mgRight.setPower(-_speedRight);
		_mgLeft.setPower(-_speedLeft);
	}

	private void fpsDrive() {
	

		

		if (Math.abs(controller.getRawAxis(1)) > .1) {
			_speedLeft = controller.getRawAxis(1);
			_speedRight = controller.getRawAxis(1);

			if (controller.getRawAxis(4) > .1) {
				if (controller.getRawAxis(1) > 0) {
					_speedRight = controller.getRawAxis(1) - controller.getRawAxis(4) * _turningAmplitude;
				} else {
					_speedRight = controller.getRawAxis(1) + controller.getRawAxis(4) * _turningAmplitude;
				}

			} else if (controller.getRawAxis(4) < -.1) {
				if (controller.getRawAxis(1) > 0) {
					_speedLeft = controller.getRawAxis(1) + controller.getRawAxis(4) * _turningAmplitude;
				} else {
					_speedLeft = controller.getRawAxis(1) - controller.getRawAxis(4) * _turningAmplitude;
				}
			}
		}
	}
}
*/
//Above is the Wireless xbox controller
//Below is for the original blue controller
//
//
//
//
//
//
//
//
//
package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {

	protected static Drive instance = null;

	private MotorGroup _mgLeft = Globals.getInstance().getMotorGroupLeft();
	private MotorGroup _mgRight = Globals.getInstance().getMotorGroupRight();

	private Controller controller = Globals.getInstance().getController();

	private double _speedRight = 0;
	private double _speedLeft = 0;
	private double _turningAmplitude = .65;
	private double _var = 0;

	private boolean _isTank = true;

	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}

	public void periodic() {
		if (_isTank) {
			TankDrive();
		} else {
			fpsDrive();
		}

		if (controller.getA()) {
			_var = .2;
		}

		if (controller.getB()) {
			_var = .3;
		}

		if (controller.getX()) {
			_var = .4;
		}

		if (controller.getY()) {
			_var = .0;
		}

	}

	private void TankDrive() {
		if (Math.abs(controller.getAxis(3)) >= .05) {
			_speedRight = controller.getAxis(3) + _var * (Math.abs(controller.getAxis(3)) / (controller.getAxis(3)));
		} else if (Math.abs(controller.getAxis(3)) < .05) {
			_speedRight = 0;
		}

		if (Math.abs(controller.getAxis(1)) >= .05) {
			_speedLeft = controller.getAxis(1) + _var * (Math.abs(controller.getAxis(3)) / (controller.getAxis(3)));
		} else if (Math.abs(controller.getAxis(1)) < .05) {
			_speedLeft = 0;
		}
		// if (controller.getLeftTrig()) {
		// fpsDrive();
		// }
		_mgRight.setPower(-_speedRight);
		_mgLeft.setPower(-_speedLeft);
		SmartDashboard.putNumber("VAR", _var);
		SmartDashboard.putNumber("Left Speed", _speedLeft);
		SmartDashboard.putNumber("Right Speed", _speedRight);
		SmartDashboard.putNumber("Right Joystick", controller.getAxis(3));
		SmartDashboard.putNumber("Left Joystick", controller.getAxis(1));

	}

	private void fpsDrive() {

		if (Math.abs(controller.getAxis(1)) > .1) {
			_speedLeft = controller.getAxis(1);
			_speedRight = controller.getAxis(1);

			if (controller.getAxis(2) > .1) {
				if (controller.getAxis(1) > 0) {
					_speedRight = controller.getAxis(1) - controller.getAxis(2) * _turningAmplitude;
				} else {
					_speedRight = controller.getAxis(1) + controller.getAxis(2) * _turningAmplitude;
				}

			} else if (controller.getAxis(2) < -.1) {
				if (controller.getAxis(1) > 0) {
					_speedLeft = controller.getAxis(1) + controller.getAxis(2) * _turningAmplitude;
				} else {
					_speedLeft = controller.getAxis(1) - controller.getAxis(2) * _turningAmplitude;
				}
			}
		}
		_speedLeft = .3;
		_speedRight = .3;
	}

	public boolean isTank() {
		return _isTank;
	}

	public void setTank(boolean b) {
		_isTank = b;
	}

	public void setPower(double left, double right) {
		_mgLeft.setPower(left);
		_mgRight.setPower(right);
	}
}
