package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.commands.drivetrain.AbsoluteDirection;
import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;
import org.usfirst.frc.team85.robot.commands.drivetrain.SetTransmissionHigh;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinExactDegrees;
import org.usfirst.frc.team85.robot.commands.drivetrain.SweepingTurn;
import org.usfirst.frc.team85.robot.commands.gripper.OpenGripper;
import org.usfirst.frc.team85.robot.commands.lift.LiftPositionWait;
import org.usfirst.frc.team85.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	public static final double DISTANCE_TO_LANE = 16;
	public static final double[] DISTANCE_TO_CUBES = new double[] { 8.25, 10.5, 13, 15.2, 17.5, 20 };
	public static final double DISTANCE_TO_LANE_CENTER_FROM_SWITCH = 3;
	public static final double DISTANCE_TO_SCALE_FROM_WALL = 8;
	public static final double DISTANCE_TO_LANE_CENTER_FROM_SCALE = 6;

	private int mult;
	private boolean scalePriority, scaleIgnore, platform, switchThenScale, secondCube, doNotCrossField;
	private String gameData;

	public Autonomous(int position, String gameData, int wait, boolean scalePriority, boolean autoLine,
			boolean platform, boolean switchThenScale, boolean secondCube, boolean scaleIgnore,
			boolean doNotCrossField) {
		this.scalePriority = scalePriority;
		this.gameData = gameData;
		this.platform = platform;
		this.switchThenScale = switchThenScale;
		this.secondCube = secondCube;
		this.scaleIgnore = scaleIgnore;
		this.doNotCrossField = doNotCrossField;

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
		}
		// else if (position == 4) {
		// mult = 1;
		// addSequential(new DriveStraight(.8, 19.5));
		// for (int i = 3; i < 6; i++) {
		// getCubeFromLane(i);
		// goToScaleFromLane();
		// }
		// }
		// else if (position == 5) {
		// // test method
		// addParallel(new DriveStraight(.5, 12).setVisionTrack());
		// addSequential(new CubeSearch());
		// }
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
				buildCloseScale();
				break;
			case "RR":
				if (platform) {
					buildFarScalePlatform();
				} else {
					buildFarScale();
				}
				break;
			}
		} else {
			char scale = gameData.charAt(1);

			switch (scale) {
			case 'L':
				buildCloseScale();
				break;
			case 'R':
				if (platform) {
					buildFarScalePlatform();
				} else {
					buildFarScale();
				}
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
				if (platform) {
					buildFarScalePlatform();
				} else {
					buildFarScale();
				}
				break;
			case "LR":
				buildCloseScale();
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
				if (platform) {
					buildFarScalePlatform();
				} else {
					buildFarScale();
				}
				break;
			case 'R':
				buildCloseScale();
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
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 9.5).setAutoShift().setAcceleration(true, false));
		addSequential(new SweepingTurn(.8, 1, -90 * mult));
		addSequential(new DriveStraight(1.0, 1.5, 1).setAcceleration(false, true));
		addSequential(new OpenGripper());

		if (secondCube) {
			addSequential(new SweepingTurn(-1.0, 1.5, -90 * mult));
			addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
			addSequential(new DriveStraight(-1.0, 4).setAcceleration(false, true));
			addSequential(new SpinExactDegrees(60 * mult));
			addSequential(new DriveStraight(.6, 2, 1.5).setAcceleration(true, false));
			addSequential(new SpinExactDegrees(-10 * mult));
			addParallel(new DriveStraight(.25, 3).setVisionTrack().setAcceleration(false, true));
			addSequential(new CubeSearch());
			addSequential(new SetTransmissionHigh(false));

			if ((gameData.charAt(1) == 'L' && mult == 1 && switchThenScale)
					|| (gameData.charAt(1) == 'R' && mult == -1 && switchThenScale)) {
				addSequential(new DriveStraight(-1.0, 1));
				addSequential(new SpinExactDegrees(170 * mult));
				addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
				addSequential(new LiftPositionWait(false));
				addSequential(new DriveStraight(1.0, 6, 2.5).setAbsoluteDirection(AbsoluteDirection.FORWARD));
				addSequential(new OpenGripper());
				addSequential(new DriveStraight(-1.0, 2));
				addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
			} else {
				addSequential(new DriveStraight(-1.0, 2));
				addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
				addSequential(new DriveStraight(1.0, 3, 1));
				addSequential(new OpenGripper());
				addSequential(new DriveStraight(-1.0, 3));
				addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
			}
		} else {
			addSequential(new DriveStraight(-.75, 3));
			addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		}
	}

	private void buildCloseScale() {
		if (scaleIgnore) {
			crossAutoLine();
			return;
		}

		addParallel(new DelayedCommand(new SetLiftHeight(Variables.LIFT_SCALE_HIGH), .5));
		addSequential(new DriveStraight(1.0, 19.5).setAbsoluteDirection(AbsoluteDirection.FORWARD)
				.setAcceleration(true, false).setAutoShift());
		addSequential(new SweepingTurn(.6, 1.5, -45 * mult));

		addSequential(new SetTransmissionHigh(false));
		addSequential(new OpenGripper());
		addSequential(new Wait(.05));
		addSequential(new DriveStraight(-1.0, 2));
		addSequential(new Wait(.05));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		if (secondCube) {
			addSequential(new SpinExactDegrees(-80 * mult));
			addSequential(new DriveStraight(1.0, 7));
			addSequential(new SpinExactDegrees(-55 * mult));
			addParallel(new DriveStraight(.25, 2.5).setVisionTrack());
			addSequential(new CubeSearch());

			addSequential(new DriveStraight(-1.0, 1.5));
			addSequential(new SpinExactDegrees(180 * mult));
			addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
			addSequential(new LiftPositionWait(false));
			addSequential(new DriveStraight(1.0, 6).setAbsoluteDirection(AbsoluteDirection.FORWARD));
			addSequential(new Wait(.05));
			addSequential(new OpenGripper());
			addSequential(new Wait(.05));
			addSequential(new DriveStraight(-1.0, 4));
			addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		}
	}

	private void buildFarScale() {
		if (scaleIgnore || doNotCrossField) {
			crossAutoLine();
			return;
		}

		addParallel(new DelayedCommand(new SetLiftHeight(Variables.LIFT_SCALE_HIGH), 2));
		addSequential(new DriveStraight(1.0, 9.5).setAutoShift().setAcceleration(true, false));
		addSequential(new SweepingTurn(1.0, 3, -90 * mult));

		if (mult == 1) {
			addSequential(new DriveStraight(1.0, 9.2).setAbsoluteDirection(AbsoluteDirection.RIGHT).setAutoShift()
					.setAcceleration(false, true));
		} else {
			addSequential(new DriveStraight(1.0, 9.2).setAbsoluteDirection(AbsoluteDirection.LEFT).setAutoShift()
					.setAcceleration(false, true));
		}

		addSequential(new SpinExactDegrees(90 * mult));
		addSequential(new DriveStraight(1.0, 4, 2.5).setAbsoluteDirection(AbsoluteDirection.FORWARD));
		addSequential(new Wait(.05));
		addSequential(new OpenGripper());
		addSequential(new Wait(.05));
		addSequential(new DriveStraight(-1.0, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		if (secondCube) {
			addSequential(new SpinExactDegrees(-180 * mult));
			addSequential(new LiftPositionWait(false));
			addSequential(new DriveStraight(1.0, 1).setAcceleration(true, false));
			addParallel(new DriveStraight(.3, 3).setVisionTrack().setAcceleration(false, true));
			addSequential(new CubeSearch());
			addSequential(new DriveStraight(-1.0, 2));
			addSequential(new SpinExactDegrees(180 * mult));
			addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
			addSequential(new LiftPositionWait(false));
			addSequential(new DriveStraight(1.0, 6, 2.5).setAbsoluteDirection(AbsoluteDirection.FORWARD));
			addSequential(new Wait(.15));
			addSequential(new OpenGripper());
			addSequential(new Wait(.05));
			addSequential(new DriveStraight(-.7, 4));
			addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		}
	}

	private void buildFarScalePlatform() {
		if (scaleIgnore || doNotCrossField) {
			crossAutoLine();
			return;
		}

		// addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		// addSequential(new DriveStraight(1.0, 16).setAcceleration(true, false));
		// addSequential(new SweepingTurn(1.0, 4, -90 * mult));
		// if (mult == 1) {
		// addSequential(new DriveStraight(.75,
		// 7.75).setAbsoluteDirection(AbsoluteDirection.RIGHT) // was 11.5
		// .setAcceleration(false, true));
		// } else {
		// addSequential(new DriveStraight(.75,
		// 7.75).setAbsoluteDirection(AbsoluteDirection.LEFT)
		// .setAcceleration(false, true));
		// }
		//
		// addSequential(new SpinExactDegrees(45 * mult, 1));
		// addSequential(new Wait(.075));
		// addSequential(new OpenGripper());
		// addSequential(new Wait(.075));
		// addSequential(new DriveStraight(-.5, 6));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		//
		// if (secondCube) {
		// addSequential(new SpinExactDegrees(-135 * mult));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new DriveStraight(.5, 2));
		// addParallel(new DriveStraight(.4, 2).setVisionTrack().setAcceleration(false,
		// true));
		// addSequential(new CubeSearch());
		// addSequential(new DriveStraight(-.75, 2));
		// addSequential(new SpinExactDegrees(135 * mult));
		// addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		// addSequential(new DriveStraight(.5, 6.5));
		// addSequential(new OpenGripper());
		// }

		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new DriveStraight(1.0, 23.35));
		addSequential(new SpinExactDegrees(-90 * mult, 1.2));
		if (mult == 1) {
			addSequential(new DriveStraight(.75, 15.5).setAbsoluteDirection(AbsoluteDirection.RIGHT));
		} else {
			addSequential(new DriveStraight(.75, 15.5).setAbsoluteDirection(AbsoluteDirection.LEFT));
		}

		addSequential(new SpinExactDegrees(45 * mult, 1));
		addSequential(new DriveStraight(.5, .5, .5));
		addSequential(new Wait(.1));
		addSequential(new OpenGripper());
		addSequential(new Wait(.1));

		if (secondCube) {
			addSequential(new DriveStraight(-.5, 5));
			addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
			addSequential(new SpinExactDegrees(-135 * mult));
			addSequential(new LiftPositionWait(false));
			addParallel(new DriveStraight(.25, 3).setVisionTrack());
			addSequential(new OpenCubeSearch());
			addSequential(new DriveStraight(-.75, 1));
		} else {
			addSequential(new SpinExactDegrees(-45 * mult, 1));
			addSequential(new DriveStraight(-.5, 3, 2));
		}
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	public void buildLeftSwitchFrom2() {
		addSequential(new DriveStraight(1.0, 1));
		addSequential(new SpinExactDegrees(45));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8.5));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(1.0, 1, 2).setAbsoluteDirection(AbsoluteDirection.FORWARD)); // RangeFinder
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-1.0, 1.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(-1.0, 8.1));
		addSequential(new SpinExactDegrees(-45));

		addSequential(new DriveStraight(.75, 3.5).setAcceleration(true, false));
		addParallel(new DriveStraight(.4, 2.5).setVisionTrack().setAcceleration(false, true));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-1.0, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));

		if (secondCube) {
			addSequential(new SpinExactDegrees(45));
			addSequential(new DriveStraight(1.0, 7.5, 3));
			addSequential(new SpinExactDegrees(-45));
			addSequential(new DriveStraight(1.0, 2, 2));
			addSequential(new OpenGripper());
			addSequential(new DriveStraight(-.7, 2));
			addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		}
	}

	public void buildRightSwitchFrom2() {
		addSequential(new DriveStraight(1.0, 1));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(1.0, 1.5, 2).setAbsoluteDirection(AbsoluteDirection.FORWARD)); // RangeFinder
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-1.0, 1.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addSequential(new SpinExactDegrees(-45));
		addSequential(new DriveStraight(-1.0, 8.3));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(.75, 3.5).setAcceleration(true, false));
		addParallel(new DriveStraight(.4, 2.5).setVisionTrack().setAcceleration(false, true));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-1.0, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));

		if (secondCube) {
			addSequential(new SpinExactDegrees(-45));
			addSequential(new DriveStraight(1.0, 6, 2));
			addSequential(new SpinExactDegrees(45));
			addSequential(new DriveStraight(1.0, 2, 2));
			addSequential(new OpenGripper());
			addSequential(new DriveStraight(-.7, 2));
			addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		}
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	public void getCubeFromLane(int cube) {
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		if (mult == 1) {
			addSequential(new DriveStraight(1.0, DISTANCE_TO_CUBES[cube] - 1.75)
					.setAbsoluteDirection(AbsoluteDirection.RIGHT).setRearRangeFinderCheck());
		} else {
			addSequential(new DriveStraight(1.0, DISTANCE_TO_CUBES[cube] - 1.75)
					.setAbsoluteDirection(AbsoluteDirection.LEFT).setRearRangeFinderCheck());
		}
		addSequential(new SpinExactDegrees(-90 * mult));
		addParallel(new DriveStraight(.4, 4).setVisionTrack());
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.8, 3.5).setRangeFinderDistance(AbsoluteDirection.FORWARD));
	}

	public void goToScaleFromLane() {
		if (gameData.charAt(1) == 'L') {
			addSequential(new DriveStraight(1.0, DISTANCE_TO_SCALE_FROM_WALL).setAutoShift()
					.setAbsoluteDirection(AbsoluteDirection.LEFT).setRangeFinderDistance(AbsoluteDirection.FORWARD));
		} else {
			addSequential(new DriveStraight(1.0, DISTANCE_TO_SCALE_FROM_WALL).setAutoShift()
					.setAbsoluteDirection(AbsoluteDirection.RIGHT).setRangeFinderDistance(AbsoluteDirection.FORWARD));
		}

		addSequential(new SpinExactDegrees(-90 * mult));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(1.0, DISTANCE_TO_LANE_CENTER_FROM_SCALE - 2));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-1.0, DISTANCE_TO_LANE_CENTER_FROM_SCALE - 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}
}