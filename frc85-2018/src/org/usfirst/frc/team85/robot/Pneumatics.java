package org.usfirst.frc.team85.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class Pneumatics {
	
	Globals globals = Globals.getInstance();

	//The status of the compressor. Whether it is running or !running.
	private boolean isCompressorOn;
	
	public Pneumatics() {
		globals.compressor = new Compressor(Addresses.compressor);
		
		globals.transmissionSolenoid = new Solenoid(Addresses.transmissionSolenoid);
	}
	
	//Compressor section
	public void setCompressor(boolean on) { //see getCompressorOn method for information on the loop.
		globals.compressor.setClosedLoopControl(on);
	}
	
	public boolean getCompressorEnabled() { //The status of the loop. 
		return globals.compressor.enabled();
	}
	
	public boolean getCompressorOn() { 
		/*  The current operating mode of the PCM. 
		 *  The PCM automatically turns the compressor on or off when this is true.
		 *  The compressor is always off when this is false.
		 */
		return globals.compressor.getClosedLoopControl();
	}
	
	public double getCompressrCurrent() { //The current draw (power) of the compressor.
		return globals.compressor.getCompressorCurrent();
	}
	
	//Transmission section
	public void setTransmission(boolean open) { //Needs testing to see if true = low gear or high gear.
		globals.transmissionSolenoid.set(open);
	}
	
	public boolean getTransmissionPosition() {
		return globals.transmissionSolenoid.get();
	}
	
}
