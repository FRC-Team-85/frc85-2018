package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class Pneumatics {
	
	private static Globals _globals = Globals.getInstance();
	
	private static Compressor _compressor = _globals.getCompressor();
	private static Solenoid _transmissionSolenoid = Globals.getInstance().getTransmissionSolenoid();

	//The status of the compressor. Whether it is running or !running.
	private boolean isCompressorOn;
	
	//Compressor section
	public void setCompressor(boolean on) { //see getCompressorOn method for information on the loop.
		_compressor.setClosedLoopControl(on);
		isCompressorOn = on; // set the compressor status to whatever was passed into here
	}
	
	public boolean getCompressorEnabled() { //The status of the loop. 
		return _compressor.enabled();
	}
	
	public boolean getCompressorOn() { 
		/*  The current operating mode of the PCM. 
		 *  The PCM automatically turns the compressor on or off when this is true.
		 *  The compressor is always off when this is false.
		 */
		return _compressor.getClosedLoopControl();
	}
	
	public double getCompressrCurrent() { //The current draw (power) of the compressor.
		return _compressor.getCompressorCurrent();
	}
	
	//Transmission section
	public void setLowGear(boolean lowGear) { //Needs testing to see if true = low gear or high gear.
		_transmissionSolenoid.set(lowGear);
	}
	
	public boolean getTransmissionPosition() {
		return _transmissionSolenoid.get();
	}
	
}
