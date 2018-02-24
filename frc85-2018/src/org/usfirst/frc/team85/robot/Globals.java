package org.usfirst.frc.team85.robot;

public class Globals {

	protected static Globals instance = null;

	private Controller controller;
	private MotorGroup mgLeft; // Left Drive Train
	private MotorGroup mgRight; // Right Drive Train

	private Globals() {
		controller = new Controller(0);

		mgLeft = new MotorGroup(new int[] { Addresses.leftBackTalon, Addresses.leftFrontTalon });
		mgRight = new MotorGroup(new int[] { Addresses.rightBackTalon, Addresses.rightFrontTalon });
	}

	public static Globals getInstance() {
		if (instance == null) {
			instance = new Globals();
		}
		return instance;
	}

	public Controller getController() {
		return controller;
	}

	public MotorGroup getMotorGroupLeft() {
		return mgLeft;
	}

	public MotorGroup getMotorGroupRight() {
		return mgRight;
	}
}