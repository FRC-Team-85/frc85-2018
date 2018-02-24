package org.usfirst.frc.team85.robot;

public class Drive {

	protected static Drive instance = null;

	private MotorGroup _mgLeft = Globals.getInstance().getMotorGroupLeft();
	private MotorGroup _mgRight = Globals.getInstance().getMotorGroupRight();

	private Controller controller = Globals.getInstance().getController();

	private double _speedRight = 0;
	private double _speedLeft = 0;

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
			_speedLeft = controller.getAxis(1);
		} else if (Math.abs(controller.getAxis(1)) < .1) {
			_speedLeft = 0;
		}

		_mgRight.setPower(-_speedRight);
		_mgLeft.setPower(-_speedLeft);
	}
}
