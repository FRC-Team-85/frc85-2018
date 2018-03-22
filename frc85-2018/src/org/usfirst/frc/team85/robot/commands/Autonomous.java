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

	public Autonomous(int position, String gameData, int wait, boolean scalePriority, boolean autoLine) {
		Encoders.getInstance().driveEncoderReset();
		if (wait != 0) {
			addSequential(new Wait(wait));
		}

		if (autoLine) {
			crossAutoLine();
		} else if (position == 1) {
			buildPosition1(gameData, scalePriority);
		} else if (position == 2) {
			buildPosition2(gameData);
		} else if (position == 3) {
			buildPosition3(gameData, scalePriority);
		}
	}

	private void buildPosition1(String gameData, boolean scalePriority) {
		if (!scalePriority) {
			gameData = gameData.substring(0, 2);
			switch (gameData) {
			case "LL":
				buildCloseSwitchFrom1();
				break;
			case "LR":
				buildCloseSwitchFrom1();
				break;
			case "RL":
				buildCloseScaleFrom1();
				break;
			case "RR":
				buildFarScaleFrom1();
				break;
			}
		} else {
			char scale = gameData.charAt(1);

			switch (scale) {
			case 'L':
				buildCloseScaleFrom1();
				break;
			case 'R':
				buildFarScaleFrom1();
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

	private void buildPosition3(String gameData, boolean scalePriority) {
		if (!scalePriority) {
			gameData = gameData.substring(0, 2);
			switch (gameData) {
			case "LL":
				buildFarScaleFrom3();
				break;
			case "LR":
				buildCloseScaleFrom3();
				break;
			case "RL":
				buildCloseSwitchFrom3();
				break;
			case "RR":
				buildCloseSwitchFrom3();
				break;
			}
		} else {
			char scale = gameData.charAt(1);

			switch (scale) {
			case 'L':
				buildFarScaleFrom3();
				break;
			case 'R':
				buildCloseScaleFrom3();
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

	private void buildCloseSwitchFrom1() {
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 6).setAbsoluteDirection(AbsoluteDirection.FORWARD).setAutoShift()
				.setAcceleration(true, false));
		addSequential(new SweepingTurn(.8, 2, -90));
		addSequential(new SetTransmissionHigh(false));
		addSequential(new OpenGripper());
		addSequential(new SweepingTurn(-.8, 3, -45));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// Second Cube
		addSequential(new DriveStraight(-.8, 3).setAcceleration(false, true));
		addSequential(new SpinExactDegrees(20));
		addParallel(new DriveStraight(.7, 6));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(.7, 1));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(.7, 2, 2));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildFarSwitchFrom1() {

	}

	private void buildCloseScaleFrom1() {
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new DriveStraight(1.0, 17).setAbsoluteDirection(AbsoluteDirection.FORWARD).setAutoShift()
				.setAcceleration(true, false));
		addSequential(new SweepingTurn(.45, 2, -45));
		addSequential(new SetTransmissionHigh(false));
		addSequential(new LiftPositionWait(false));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 4.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// Second Cube
		addSequential(new LiftPositionWait(false));
		addSequential(new SpinExactDegrees(-100));
		addParallel(new DriveStraight(.6, 12));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.6, 1));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(135));
		addSequential(new DriveStraight(.7, 4));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.7, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildFarScaleFrom1() {
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8).setAbsoluteDirection(AbsoluteDirection.FORWARD).setAutoShift()
				.setAcceleration(true, false));
		addSequential(new SweepingTurn(1.0, 3, -90));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new DriveStraight(1.0, 8).setAbsoluteDirection(AbsoluteDirection.RIGHT));
		addSequential(new SpinExactDegrees(90));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 4.5, 3));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 4));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// Second Cube
		addSequential(new SpinExactDegrees(-180));
		addParallel(new DriveStraight(.5, 5).setVisionTrack());
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.5, 3));

		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(180));
		addSequential(new DriveStraight(.8, 3.5, 3));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 3));
		addSequential(new SpinExactDegrees(-135));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	public void buildLeftSwitchFrom2() {
		addSequential(new DriveStraight(.75, 1));
		addSequential(new SpinExactDegrees(45));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8.5));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 1, 2).setAbsoluteDirection(AbsoluteDirection.FORWARD));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 1.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// Second Cube
		// addSequential(new SpinExactDegrees(-90));
		// addParallel(new DriveStraight(.35,
		// 6).setAbsoluteDirection(AbsoluteDirection.RIGHT));
		// addSequential(new CubeSearch());
		// addSequential(new DriveStraight(-.35, 5));
		// addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new SpinExactDegrees(90));
		// addSequential(new DriveStraight(.5, 2, 3));
		// addSequential(new OpenGripper());
		// addSequential(new DriveStraight(-.6, 1));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	public void buildRightSwitchFrom2() {
		addSequential(new DriveStraight(.75, 1));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8.5));
		addSequential(new SpinExactDegrees(45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 1, 2).setAbsoluteDirection(AbsoluteDirection.FORWARD));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 1.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// Second Cube
		// addSequential(new SpinExactDegrees(90));
		// addParallel(new DriveStraight(.35,
		// 7).setAbsoluteDirection(AbsoluteDirection.LEFT)); // diff
		// addSequential(new CubeSearch());
		// addSequential(new DriveStraight(-.35, 5));
		// addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new SpinExactDegrees(-90));
		// addSequential(new DriveStraight(.5, 2, 3));
		// addSequential(new OpenGripper());
		// addSequential(new DriveStraight(-.6, 1));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	private void buildCloseSwitchFrom3() {
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 12));
		addSequential(new SpinExactDegrees(90));
		addSequential(new Wait(.15));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.5, 2, 3));
		addSequential(new OpenGripper());
		addSequential(new Wait(.2));
		addSequential(new DriveStraight(-.5, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addSequential(new SpinExactDegrees(-90, 2));
		addSequential(new DriveStraight(1.0, 6.5));
		addSequential(new SpinExactDegrees(90, 2));
		addSequential(new DriveStraight(.8, 7).setAbsoluteDirection(AbsoluteDirection.LEFT));
		addSequential(new SpinExactDegrees(90));
		addParallel(new DriveStraight(.8, 3, 3));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.8, 3));
	}

	private void buildFarSwitchFrom3() {

	}

	private void buildCloseScaleFrom3() {
		addParallel(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new DriveStraight(1.0, 22).setAbsoluteDirection(AbsoluteDirection.FORWARD));
		addSequential(new SpinExactDegrees(45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 2.5, 3));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 3.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		addSequential(new SpinExactDegrees(90));
		addSequential(new LiftPositionWait(false));

		addParallel(new DriveStraight(.8, 10));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.8, 4));

		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(-135));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.8, 3.5));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		addSequential(new SpinExactDegrees(135));
	}

	private void buildFarScaleFrom3() {
		addSequential(new DriveStraight(1.0, 18));
		addSequential(new SpinExactDegrees(90));
		addSequential(new DriveStraight(1.0, 16.5).setAbsoluteDirection(AbsoluteDirection.LEFT));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 4.5, 3));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 4.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}
}