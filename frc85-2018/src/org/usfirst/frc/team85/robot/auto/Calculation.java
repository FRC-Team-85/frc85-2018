package org.usfirst.frc.team85.robot.auto;

public class Calculation {

	public static final double µ = .64;
	public static final double g = 32.18504; // ft/s^2

	public static double[] getXY(double angle, double x, double y) {
		return new double[] { x * Math.cos(angle), y * Math.cos(angle) };
	}

	public static double getMinimumRadius(double v) {
		double radius = v * v / µ / g;
		return radius;
	}

	public static double[] calcTurn(double angle, double[] xy, double vel) {
		double[] output = new double[2];
		// double radius = getMinimumRadius(vel);

		return output;
	}
}
