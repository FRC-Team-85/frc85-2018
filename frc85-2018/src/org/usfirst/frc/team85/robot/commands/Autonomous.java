package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.commands.drivetrain.AbsoluteDirection;
import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;
import org.usfirst.frc.team85.robot.commands.drivetrain.SetTransmissionHigh;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinExactDegrees;
import org.usfirst.frc.team85.robot.commands.drivetrain.SweepingTurn;
import org.usfirst.frc.team85.robot.commands.gripper.CloseGripper;
import org.usfirst.frc.team85.robot.commands.gripper.OpenGripper;
import org.usfirst.frc.team85.robot.commands.intake.IntakeLimitWait;
import org.usfirst.frc.team85.robot.commands.lift.LiftPositionWait;
import org.usfirst.frc.team85.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	public static final double DISTANCE_TO_LANE = 16;
	public static final double[] DISTANCE_TO_CUBES = new double[] { 7, 9, 11, 13, 15, 17 };
	public static final double DISTANCE_TO_LANE_CENTER_FROM_SWITCH = 3;
	public static final double DISTANCE_TO_SCALE_FROM_WALL = 8;
	public static final double DISTANCE_TO_LANE_CENTER_FROM_SCALE = 6;

	private int mult;
	private boolean scalePriority, dragRace;
	private String gameData;

	public Autonomous(int position, String gameData, int wait, boolean scalePriority, boolean autoLine,
			boolean dragRace) {
		this.scalePriority = scalePriority;
		this.dragRace = dragRace;
		this.gameData = gameData;

		if (wait != 0) {
			addSequential(new Wait(wait));
		}

		if (position == 2) {
			buildPosition2();
		} else if (autoLine) {
			crossAutoLine();
		} else if (position == 1) {
			mult = 1;
			buildPosition1();
		} else if (position == 3) {
			mult = -1;
			buildPosition3();
		} else {
			// test method - logic to choose need implementation
			mult = 1;
			buildFarScalePlatform();
		}
	}

	private void buildPosition1() {
		if (!scalePriority) {
			String half = gameData.substring(0, 2);
			switch (half) {
			case "LL":
				buildCloseSwitch();
				break;
			case "LR":
				buildCloseSwitch();
				break;
			case "RL":
				if (dragRace) {
					buildDragRaceToCloseScale();
				} else {
					buildCloseScale();
				}
				break;
			case "RR":
				buildFarScale();
				break;
			}
		} else {
			char scale = gameData.charAt(1);

			switch (scale) {
			case 'L':
				if (dragRace) {
					buildDragRaceToCloseScale();
				} else {
					buildCloseScale();
				}
				break;
			case 'R':
				buildFarScale();
				break;
			}
		}
	}

	private void buildPosition2() {
		String swi = gameData.substring(0, 1);
		switch (swi) {
		case "L":
			buildLeftSwitchFrom2();
			break;
		case "R":
			buildRightSwitchFrom2();
			break;
		}
	}

	private void buildPosition3() {
		if (!scalePriority) {
			String half = gameData.substring(0, 2);
			switch (half) {
			case "LL":
				buildFarScale();
				break;
			case "LR":
				if (dragRace) {
					buildDragRaceToCloseScale();
				} else {
					buildCloseScale();
				}
				break;
			case "RL":
				buildCloseSwitch();
				break;
			case "RR":
				buildCloseSwitch();
				break;
			}
		} else {
			char scale = gameData.charAt(1);

			switch (scale) {
			case 'L':
				buildFarScale();
				break;
			case 'R':
				if (dragRace) {
					buildDragRaceToCloseScale();
				} else {
					buildCloseScale();
				}
				break;
			}
		}
	}

	@Override
	protected void initialize() {
		super.initialize();
		IMU.getInstance().setInitialHeading();
		DriveTrain.getInstance().setHighGear(false);
		Encoders.getInstance().driveEncoderReset();
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	private void crossAutoLine() {
		addSequential(new DriveStraight(.75, 11));
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	private void buildCloseSwitch() {
		// addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		// addSequential(new DriveStraight(1.0, 12).setAutoShift());
		// addSequential(new SpinExactDegrees(-90 * mult));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new DriveStraight(.6, 1.8, 2));
		// addSequential(new OpenGripper());
		// addSequential(new Wait(.1));
		// addSequential(new DriveStraight(-.6, 2));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		//
		// // Second Cube
		// addSequential(new SpinExactDegrees(90 * mult));
		// addSequential(new DriveStraight(1.0, 8));
		// addSequential(new SpinExactDegrees(-90 * mult));
		//
		// if (mult == 1) {
		// addSequential(new DriveStraight(.8,
		// 6.5).setAbsoluteDirection(AbsoluteDirection.RIGHT)); // Use RangeFinders
		// } else {
		// addSequential(new DriveStraight(.8,
		// 6.5).setAbsoluteDirection(AbsoluteDirection.LEFT));
		// }
		//
		// addSequential(new SpinExactDegrees(-90 * mult));
		// addParallel(new DriveStraight(.7, 3, 3));
		// addSequential(new CubeSearch());
		// addSequential(new DriveStraight(-.7, 3));

		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8).setAutoShift().setAcceleration(true, false));
		addSequential(new SweepingTurn(.8, 1, -90 * mult));
		addSequential(new DriveStraight(1.0, 2, 1.5));
		addSequential(new Wait(.2));
		addSequential(new OpenGripper());

		addSequential(new DelayedCommand(new SetLiftHeight(Variables.LIFT_GROUND), 1));
		addSequential(new SweepingTurn(-.8, 2, -90 * mult));
		addSequential(new DriveStraight(-1, 6).setAcceleration(false, false));
		addSequential(new SweepingTurn(-.5, 1, 45 * mult));

		addParallel(new DriveStraight(.65, 7));
		addSequential(new CubeSearch());
		addParallel(new DriveStraight(-.65, 2));

		fromCube1ToScale();
	}

	private void buildCloseScale() {
		// addParallel(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		// addSequential(new DriveStraight(1.0,
		// 22).setAbsoluteDirection(AbsoluteDirection.FORWARD).setAutoShift()); // Shift
		// addSequential(new SpinExactDegrees(-45 * mult));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new DriveStraight(.75, 3.5, 3));
		// addSequential(new OpenGripper());
		// addSequential(new DriveStraight(-.75, 4.5));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		// addSequential(new SpinExactDegrees(-90 * mult));
		// addSequential(new LiftPositionWait(false));
		//
		// addParallel(new DriveStraight(.8, 10));
		// addSequential(new CubeSearch());
		// addSequential(new DriveStraight(-.8, 2.5));
		//
		// addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		// addSequential(new SpinExactDegrees(135 * mult));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new DriveStraight(.8, 4.5));
		// addSequential(new OpenGripper());
		// addSequential(new DriveStraight(-.8, 3.5));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		// addSequential(new SpinExactDegrees(-135 * mult));
	}

	private void buildDragRaceToCloseScale() {
		addParallel(new DelayedCommand(new SetLiftHeight(Variables.LIFT_SCALE_HIGH), .5));
		addSequential(new DriveStraight(1.0, 17).setAbsoluteDirection(AbsoluteDirection.FORWARD)
				.setAcceleration(true, false).setAutoShift());
		addSequential(new SweepingTurn(.7, 2, -45 * mult));
		addSequential(new SetTransmissionHigh(false));
		addSequential(new LiftPositionWait(false));
		addSequential(new OpenGripper());

		// addSequential(new Wait(.25));
		// addSequential(new DriveStraight(-.8, 2));
		// addSequential(new Wait(.25));
		// addSequential(new SpinExactDegrees(-85 * mult));

		addSequential(new SweepingTurn(-.7, 2, -35 * mult));

		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		addSequential(new LiftPositionWait(false));
		addParallel(new DriveStraight(.7, 12));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.7, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(35 * mult));
		addSequential(new DriveStraight(.7, 5));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.7, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildFarScale() {
		addParallel(new DelayedCommand(new SetLiftHeight(Variables.LIFT_SCALE_HIGH), 2));
		addSequential(new DriveStraight(1.0, 10.5).setAutoShift().setAcceleration(true, false));
		addSequential(new SweepingTurn(1.0, 3, -90 * mult));

		if (mult == 1) {
			addSequential(new DriveStraight(1.0, 7).setAbsoluteDirection(AbsoluteDirection.RIGHT).setAutoShift()
					.setAcceleration(false, false)); // Shift
		} else {
			addSequential(new DriveStraight(1.0, 7).setAbsoluteDirection(AbsoluteDirection.LEFT).setAutoShift()
					.setAcceleration(false, true));
		}

		addSequential(new SpinExactDegrees(90 * mult));
		addSequential(new DriveStraight(.7, 4, 2.5));
		addSequential(new Wait(.2));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.75, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addParallel(new DriveStraight(.7, 6).setAbsoluteDirection(AbsoluteDirection.BACKWARD));
		addSequential(new CubeSearch());
	}

	private void buildFarScalePlatform() {
		// addSequential(new DriveStraight(1.0, 19.5).setAutoShift()); // Shift
		// addSequential(new SpinExactDegrees(-90 * mult));
		// addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		//
		// if (mult == 1) {
		// addSequential(new DriveStraight(1.0,
		// 16.5).setAbsoluteDirection(AbsoluteDirection.RIGHT).setAutoShift()); // Shift
		// } else {
		// addSequential(new DriveStraight(1.0,
		// 16.5).setAbsoluteDirection(AbsoluteDirection.LEFT).setAutoShift());
		// }
		//
		// addSequential(new SpinExactDegrees(90 * mult));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new DriveStraight(.8, 5.5, 3));
		// addSequential(new OpenGripper());
		// addSequential(new DriveStraight(-.6, 5));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		//
		// // Second Cube
		// addSequential(new SpinExactDegrees(-180 * mult));
		// addParallel(new DriveStraight(.5, 3));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new CubeSearch());
		// addSequential(new DriveStraight(-.5, 2));
		// addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		// addSequential(new SpinExactDegrees(180 * mult));
		//
		// addSequential(new LiftPositionWait(false));
		// addSequential(new DriveStraight(.8, 3.5, 3));
		// addSequential(new OpenGripper());
		// addSequential(new DriveStraight(-.8, 3));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addParallel(new DelayedCommand(new SetLiftHeight(Variables.LIFT_SWITCH), .5));
		addSequential(new DriveStraight(1.0, 11.15).setAutoShift().setAcceleration(true, false));
		addSequential(new SweepingTurn(1.0, 3, -90 * mult));
		addSequential(new SetTransmissionHigh(false));
		addParallel(new DelayedCommand(new SetLiftHeight(Variables.LIFT_SCALE_HIGH), .1));

		if (mult == 1) {
			addSequential(new DriveStraight(.75, 7).setAbsoluteDirection(AbsoluteDirection.RIGHT).setAutoShift()
					.setAcceleration(false, true)); // Shift
		} else {
			addSequential(new DriveStraight(.75, 7).setAbsoluteDirection(AbsoluteDirection.LEFT).setAutoShift()
					.setAcceleration(false, true));
		}

		addSequential(new SpinExactDegrees(30 * mult));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.7, 1, 1));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.5, 5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	public void buildLeftSwitchFrom2() {
		addSequential(new DriveStraight(.9, 1));
		addSequential(new SpinExactDegrees(45));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8.5));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.7, 1.5, 2).setAbsoluteDirection(AbsoluteDirection.FORWARD)); // RangeFinder
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 1.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(-1.0, 8.1));
		addSequential(new SpinExactDegrees(-45));

		// Second Cube
		addParallel(new DriveStraight(.6, 6.5));
		addSequential(new OpenGripper());
		addSequential(new IntakeLimitWait());
		addSequential(new CloseGripper());
		addSequential(new Wait(.75));

		addSequential(new DriveStraight(-.8, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(1.0, 6.5));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new DriveStraight(.8, 2, 2));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.7, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	public void buildRightSwitchFrom2() {
		addSequential(new DriveStraight(.9, 1));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8));
		addSequential(new SpinExactDegrees(45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.7, 1.5, 2).setAbsoluteDirection(AbsoluteDirection.FORWARD)); // RangeFinder
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 1.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new DriveStraight(-1.0, 8.3));
		addSequential(new SpinExactDegrees(45));

		// Second Cube
		addParallel(new DriveStraight(.6, 6.5));
		addSequential(new OpenGripper());
		addSequential(new IntakeLimitWait());
		addSequential(new CloseGripper());
		addSequential(new Wait(.75));

		addSequential(new DriveStraight(-.8, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new DriveStraight(1.0, 6.5));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(.8, 2, 2));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.7, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	public void getCubeFromLane(int cube) {
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		if (mult == 1) {
			addSequential(new DriveStraight(1.0, DISTANCE_TO_CUBES[cube]).setAbsoluteDirection(AbsoluteDirection.RIGHT)
					.setAutoShift().setRangeFinderDistance(AbsoluteDirection.BACKWARD));
		} else {
			addSequential(new DriveStraight(1.0, DISTANCE_TO_CUBES[cube]).setAbsoluteDirection(AbsoluteDirection.LEFT)
					.setAutoShift().setRangeFinderDistance(AbsoluteDirection.BACKWARD));
		}
		addSequential(new SpinExactDegrees(-90 * mult));
		addParallel(new DriveStraight(.8, 6));
		addSequential(new CubeSearch());
		addParallel(new DriveStraight(-.8, DISTANCE_TO_LANE_CENTER_FROM_SWITCH)
				.setRangeFinderDistance(AbsoluteDirection.FORWARD));
	}

	public void goToScaleFromLane() {
		if (gameData.charAt(1) == 'L') {
			addSequential(new DriveStraight(1.0, DISTANCE_TO_SCALE_FROM_WALL).setAutoShift()
					.setAbsoluteDirection(AbsoluteDirection.LEFT).setRangeFinderDistance(AbsoluteDirection.FORWARD));
			addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH_DOUBLE));
			addSequential(new SpinExactDegrees(-90));
		} else {
			addSequential(new DriveStraight(1.0, DISTANCE_TO_SCALE_FROM_WALL).setAutoShift()
					.setAbsoluteDirection(AbsoluteDirection.RIGHT).setRangeFinderDistance(AbsoluteDirection.FORWARD));
			addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH_DOUBLE));
			addSequential(new SpinExactDegrees(90));
		}

		addSequential(new DriveStraight(.8, DISTANCE_TO_LANE_CENTER_FROM_SCALE - 2));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, DISTANCE_TO_LANE_CENTER_FROM_SCALE - 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void fromCube1ToScale() {
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new DriveStraight(.8, 7, 4).setAbsoluteDirection(AbsoluteDirection.FORWARD));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void fromCube1ToSwitch() {
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(.8, 3, 3));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}
}