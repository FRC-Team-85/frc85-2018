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

		if (Math.abs(controller.getAxis(3)) >= .1) {
			_speedRight = controller.getAxis(3);
		} else if (Math.abs(controller.getAxis(3)) < .1) {
			_speedRight = 0;
		}

		if (Math.abs(controller.getAxis(1)) >= .1) {
			_speedLeft = controller.getAxis(1) ;
		} else if (Math.abs(controller.getAxis(1)) < .1) {
			_speedLeft = 0;
		}
		if (controller.getLeftTrig()) {
		fpsDrive();
		}
		_mgRight.setPower(-_speedRight);
		_mgLeft.setPower(-_speedLeft);
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
	}
}
