package org.usfirst.frc.team85.robot.auto.InputSource;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public abstract class InputSource extends PIDSubsystem {

	private static double P = .75, I = 0.5, D = 0.0;

	public InputSource() {
		super(P, I, D);
	}

	public abstract double[] getCorrectionValues();

	public abstract boolean isSatisfied();
}
