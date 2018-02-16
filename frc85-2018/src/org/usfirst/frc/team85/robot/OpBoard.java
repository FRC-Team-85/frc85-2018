package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OpBoard {

	protected static OpBoard instance = null;
	
	private Joystick _opBoard = new Joystick(Addresses.opBoardStick);
	
	public static OpBoard getInstance() {
		if (instance == null) {
			instance = new OpBoard();
		}
		return instance;
	}
	
	/*
	 * let's talk about the stupid fuck that this stick is
	 * towards the bottom left, the controller returns an x of 1 and a y of -1
	 * towards the very bottom, the controller returns a y of -1
	 * everything else returns 0
	 */
	
	public double getLiftStickX() {
		return _opBoard.getRawAxis(0);
	}
	
	public double getLiftStickY() {
		return _opBoard.getRawAxis(1);
	}
	
	public boolean getLiftZeroButton() { // button that sets pos to zero
		return _opBoard.getRawButton(0);
	}
	
	public boolean getLiftTwoButton() { // button that sets pos to two inches
		return _opBoard.getRawButton(1);
	}
	
	public boolean getLiftOneButton() { // button that sets pos to 1.5'
		return _opBoard.getRawButton(2);
	}
	
	public boolean getLiftFourButton() { // button that sets pos to 4.25'
		return _opBoard.getRawButton(3);
	}
	
	public boolean getLiftSixButton() { // button that sets pos to 6.25'
		return _opBoard.getRawButton(4);
	}
	
	public boolean getLiftEightButton() { // button that sets pos to 8'
		return _opBoard.getRawButton(5);
	}
	
	public boolean getVaultDeliverButton() { // button that delivers the cube to the vault
		return _opBoard.getRawButton(6);
	}
	
	public boolean getDropOverrideButton() {
		return _opBoard.getRawButton(7);
	}
	
	public boolean getCompressorOnButton() {
		return _opBoard.getRawButton(8);
	}
	
	public boolean getCompressorOffButton() {
		return _opBoard.getRawButton(9);
	}
	
	public boolean getHighTransmissionButton() {
		return _opBoard.getRawButton(10);
	}
	
	public boolean getLowTransmissionButton() {
		return _opBoard.getRawButton(11);
	}
	
	public boolean getLeftRampDropButton() {
		return _opBoard.getRawButton(12);
	}
	
	public boolean getRightRampDropButton() {
		return _opBoard.getRawButton(13);
	}
	
}
