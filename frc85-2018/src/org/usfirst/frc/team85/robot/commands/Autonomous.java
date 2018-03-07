package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.commands.drivetrain.AbsoluteDirection;
import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinExactDegrees;
import org.usfirst.frc.team85.robot.commands.gripper.OpenGripper;
import org.usfirst.frc.team85.robot.commands.lift.LiftPositionWait;
import org.usfirst.frc.team85.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team85.robot.sensors.Encoders;
import org.usfirst.frc.team85.robot.sensors.IMU;
import org.usfirst.frc.team85.robot.subsystems.DriveTrain;
import org.usfirst.frc.team85.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	public Autonomous(int position, String gameData) {
		if (position == 1) {
			buildPosition1(gameData);
		} else if (position == 2) {
			buildPosition2(gameData);
		} else if (position == 3) {
			buildPosition3(gameData);
		}
	}

	private void buildPosition1(String gameData) {
		// gameData = gameData.substring(0, 2);
		// switch (gameData) {
		// case "LL":
		// buildCloseScaleFrom1();
		// break;
		// case "LR":
		// buildCloseSwitchFrom1();
		// break;
		// case "RL":
		// buildFarSwitchFrom1();
		// break;
		// case "RR":
		// buildFarScaleFrom1();
		// break;
		// }

		char gD = gameData.charAt(0);
		switch (gD) {
		case 'L':
			buildCloseSwitchFrom1();
			break;
		case 'R':
			buildFarSwitchFrom1();
			break;
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

	private void buildPosition3(String gameData) {
		// gameData = gameData.substring(0, 2);
		// switch (gameData) {
		// case "RR":
		// buildCloseScaleFrom3();
		// break;
		// case "RL":
		// buildCloseSwitchFrom3();
		// break;
		// case "LR":
		// buildFarSwitchFrom3();
		// break;
		// case "LL":
		// buildFarScaleFrom3();
		// break;
		// }

		char gD = gameData.charAt(0);
		switch (gD) {
		case 'R':
			buildCloseSwitchFrom3();
			break;
		case 'L':
			buildFarSwitchFrom3();
			break;
		}
	}

	@Override
	protected void initialize() {
		super.initialize();
		IMU.getInstance().setInitialHeading();
		DriveTrain.getInstance().setHighGear(false);
		Lift.getInstance().setDesiredHeight(500);
		Encoders.getInstance().driveEncoderReset();
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	private void buildCloseSwitchFrom1() {
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 12));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new Wait(.3));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.5, 2));
		addSequential(new OpenGripper());
		addSequential(new Wait(.2));
		addSequential(new DriveStraight(-.5, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// addSequential(new SpinExactDegrees(90));
		// addSequential(new DriveStraight(1.0, 6.5));
		// addSequential(new SpinExactDegrees(-90));
		// addSequential(new DriveStraight(.8, 8, AbsoluteDirection.RIGHT));
		// addSequential(new SpinExactDegrees(-90));
		// addParallel(new DriveStraight(.8, 5));
		// addSequential(new CubeSearch());
		// addSequential(new DriveStraight(-.8, 3));
		//
		// addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new DriveStraight(.5, 3));
		// addSequential(new OpenGripper());
		// addSequential(new Wait(.2));
		// addSequential(new DriveStraight(-.5, 3));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildFarSwitchFrom1() {
		addSequential(new DriveStraight(1.0, 18.5));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 17.5, AbsoluteDirection.RIGHT));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 4));
		addSequential(new OpenGripper());
		addSequential(new Wait(.1));
		addSequential(new DriveStraight(-.6, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildCloseScaleFrom1() {
		addParallel(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new DriveStraight(1.0, 22, AbsoluteDirection.FORWARD));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 2.5));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 3.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new LiftPositionWait(false));

		addParallel(new DriveStraight(.8, 10));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.8, 4));

		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(135));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.8, 2));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 3));
		addSequential(new SpinExactDegrees(-135));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildFarScaleFrom1() {
		addSequential(new DriveStraight(1.0, 18.5));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new DriveStraight(1.0, 17.5, AbsoluteDirection.RIGHT));
		addSequential(new SpinExactDegrees(90));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 4));
		addSequential(new Wait(.3));
		addSequential(new OpenGripper());
		addSequential(new Wait(.3));
		addSequential(new DriveStraight(-.6, 4));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	public void buildLeftSwitchFrom2() {
		addSequential(new DriveStraight(.75, 1));
		addSequential(new SpinExactDegrees(45));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 1, AbsoluteDirection.FORWARD));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	public void buildRightSwitchFrom2() {
		addSequential(new DriveStraight(.75, 1));
		addSequential(new SpinExactDegrees(-45));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 8));
		addSequential(new SpinExactDegrees(45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 1, AbsoluteDirection.FORWARD));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	// ***********************************************************************//
	// ***********************************************************************//
	// ***********************************************************************//

	private void buildCloseSwitchFrom3() {
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 12));
		addSequential(new SpinExactDegrees(90));
		addSequential(new Wait(.3));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.5, 2));
		addSequential(new OpenGripper());
		addSequential(new Wait(.2));
		addSequential(new DriveStraight(-.5, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// addSequential(new SpinExactDegrees(-90));
		// addSequential(new DriveStraight(1.0, 6.5));
		// addSequential(new SpinExactDegrees(90));
		// addSequential(new DriveStraight(.8, 4));
		// addSequential(new SpinExactDegrees(90));
		// addParallel(new DriveStraight(.8, 5));
		// addSequential(new CubeSearch());
		// addSequential(new DriveStraight(-.8, 3));
		//
		// addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		// addSequential(new LiftPositionWait(false));
		// addSequential(new DriveStraight(.5, 3));
		// addSequential(new OpenGripper());
		// addSequential(new Wait(.2));
		// addSequential(new DriveStraight(-.5, 3));
		// addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildFarSwitchFrom3() {
		addSequential(new DriveStraight(1.0, 18.5));
		addSequential(new SpinExactDegrees(90));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(1.0, 17.5, AbsoluteDirection.LEFT));
		addSequential(new SpinExactDegrees(90));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 4));
		addSequential(new OpenGripper());
		addSequential(new Wait(.1));
		addSequential(new DriveStraight(-.6, 2));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildCloseScaleFrom3() {
		addParallel(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new DriveStraight(1.0, 22, AbsoluteDirection.FORWARD));
		addSequential(new SpinExactDegrees(45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 2.5));
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
		addSequential(new DriveStraight(.8, 2));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 3));
		addSequential(new SpinExactDegrees(135));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildFarScaleFrom3() {
		addSequential(new DriveStraight(1.0, 18.5));
		addSequential(new SpinExactDegrees(90));
		addSequential(new DriveStraight(1.0, 17.5, AbsoluteDirection.LEFT));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 4));
		addSequential(new Wait(.3));
		addSequential(new OpenGripper());
		addSequential(new Wait(.3));
		addSequential(new DriveStraight(-.6, 4));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}
}