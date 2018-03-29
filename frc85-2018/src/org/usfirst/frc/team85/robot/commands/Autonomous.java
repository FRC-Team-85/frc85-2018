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

	public Autonomous(int position, String gameData, int wait, boolean scalePriority, boolean autoLine,
			boolean dragRace) {
		Encoders.getInstance().driveEncoderReset();
		if (wait != 0) {
			addSequential(new Wait(wait));
		}

		if (autoLine) {
			crossAutoLine();
		} else if (position == 1) {
			buildPosition1(gameData, scalePriority, dragRace);
		} else if (position == 2) {
			buildPosition2(gameData);
		} else if (position == 3) {
			buildPosition3(gameData, scalePriority, dragRace);
		}
	}

	private void buildPosition1(String gameData, boolean scalePriority, boolean dragRace) {
		if (!scalePriority) {
			gameData = gameData.substring(0, 2);
			switch (gameData) {
			case "LL":
				buildCloseSwitch(1);
				break;
			case "LR":
				buildCloseSwitch(1);
				break;
			case "RL":
				if (dragRace) {
					buildDragRaceToCloseScale(1);
				} else {
					buildCloseScale(1);
				}
				break;
			case "RR":
				buildFarScale(1);
				break;
			}
		} else {
			char scale = gameData.charAt(1);

			switch (scale) {
			case 'L':
				if (dragRace) {
					buildDragRaceToCloseScale(1);
				} else {
					buildCloseScale(1);
				}
				break;
			case 'R':
				buildFarScale(1);
				break;
			}
		}
	}

	private void buildPosition2(String gameData) {
		gameData = gameData.substring(0, 1);
		switch (gameData) {
		case "L":
			buildLeftSwitchFrom2();
			break;
		case "R":
			buildRightSwitchFrom2();
			break;
		}
	}

	private void buildPosition3(String gameData, boolean scalePriority, boolean dragRace) {
		if (!scalePriority) {
			gameData = gameData.substring(0, 2);
			switch (gameData) {
			case "LL":
				buildFarScale(-1);
				break;
			case "LR":
				if (dragRace) {
					buildDragRaceToCloseScale(-1);
				} else {
					buildCloseScale(-1);
				}
				break;
			case "RL":
				buildCloseSwitch(-1);
				break;
			case "RR":
				buildCloseSwitch(-1);
				break;
			}
		} else {
			char scale = gameData.charAt(1);

			switch (scale) {
			case 'L':
				buildFarScale(-1);
				break;
			case 'R':
				if (dragRace) {
					buildDragRaceToCloseScale(-1);
				} else {
					buildCloseScale(-1);
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

	private void buildCloseSwitch(int mult) {
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 12).setAutoShift());
		addSequential(new SpinExactDegrees(-90 * mult));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 1.8, 2));
		addSequential(new OpenGripper());
		addSequential(new Wait(.1));
		addSequential(new DriveStraight(-.6, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// Second Cube
		addSequential(new SpinExactDegrees(90 * mult));
		addSequential(new DriveStraight(1.0, 8));
		addSequential(new SpinExactDegrees(-90 * mult));

		if (mult == 1) {
			addSequential(new DriveStraight(.8, 6.5).setAbsoluteDirection(AbsoluteDirection.RIGHT)); // Use RangeFinders
		} else {
			addSequential(new DriveStraight(.8, 6.5).setAbsoluteDirection(AbsoluteDirection.LEFT));
		}

		addSequential(new SpinExactDegrees(-90 * mult));
		addParallel(new DriveStraight(.7, 3, 3));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.7, 3));
	}

	private void buildFarSwitchFrom1() {

	}

	private void buildCloseScale(int mult) {
		addParallel(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new DriveStraight(1.0, 22).setAbsoluteDirection(AbsoluteDirection.FORWARD).setAutoShift()); // Shift
		addSequential(new SpinExactDegrees(-45 * mult));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.75, 3.5, 3));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.75, 4.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		addSequential(new SpinExactDegrees(-90 * mult));
		addSequential(new LiftPositionWait(false));

		addParallel(new DriveStraight(.8, 10));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.8, 2.5));

		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(135 * mult));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.8, 4.5));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 3.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		addSequential(new SpinExactDegrees(-135 * mult));
	}

	private void buildDragRaceToCloseScale(int mult) {
		addSequential(new SetTransmissionHigh(true));
		addParallel(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new DriveStraight(1.0, 17).setAbsoluteDirection(AbsoluteDirection.FORWARD).setAcceleration(true,
				false));
		addSequential(new SweepingTurn(.6, 2, -45 * mult));
		addSequential(new SetTransmissionHigh(false));
		addSequential(new LiftPositionWait(false));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addSequential(new LiftPositionWait(false));
		addSequential(new SpinExactDegrees(-100 * mult));
		addParallel(new DriveStraight(.7, 12));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.6, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(145 * mult));
		addSequential(new DriveStraight(.7, 5));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.7, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildFarScale(int mult) {
		addSequential(new DriveStraight(1.0, 19.5).setAutoShift()); // Shift
		addSequential(new SpinExactDegrees(-90 * mult));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));

		if (mult == 1) {
			addSequential(new DriveStraight(1.0, 16.5).setAbsoluteDirection(AbsoluteDirection.RIGHT).setAutoShift()); // Shift
		} else {
			addSequential(new DriveStraight(1.0, 16.5).setAbsoluteDirection(AbsoluteDirection.LEFT).setAutoShift());
		}

		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(90 * mult));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.8, 5.5, 3));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// Second Cube
		addSequential(new SpinExactDegrees(-180 * mult));
		addParallel(new DriveStraight(.5, 3));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.5, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(180 * mult));

		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.8, 3.5, 3));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 3));
		addSequential(new SpinExactDegrees(-135 * mult));
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
		addParallel(new DriveStraight(.8, 6.5));
		addSequential(new OpenGripper());
		addSequential(new IntakeLimitWait());
		addSequential(new CloseGripper());
		addSequential(new Wait(.75));

		addSequential(new DriveStraight(-.8, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(1.0, 6.5));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new DriveStraight(.8, 2));
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
		addParallel(new DriveStraight(.8, 6.5));
		addSequential(new OpenGripper());
		addSequential(new IntakeLimitWait());
		addSequential(new CloseGripper());
		addSequential(new Wait(.75));

		addSequential(new DriveStraight(-.8, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new DriveStraight(1.0, 6.5));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(.8, 2));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.7, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}
}