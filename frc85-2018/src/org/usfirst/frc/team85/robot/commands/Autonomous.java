package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinDegrees;
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

	public Autonomous(String fieldKey) {
		switch (fieldKey) {
		case "LLL":
			break;
		case "LRL":
			buildSwitchFrom3();
			break;
		case "RLR":
			buildCloseScaleFrom3();
			break;
		case "RRR":
			buildOppositeScaleFrom3();
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

	// private void driveStraightSearchCube() {
	// addParallel(new DriveStraight(.25, 100));
	// addSequential(new CubeSearch());
	// addSequential(new DriveStraight(0, 0));
	// }

	private void buildSwitchFrom3() {
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(.5, 10)); // dist
		addSequential(new SpinExactDegrees(-90));
		addSequential(new Wait(.4));
		addSequential(new OpenGripper());
		addSequential(new Wait(.3));
		addSequential(new DriveStraight(-.3, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildCloseScaleFrom3() {
		addSequential(new DriveStraight(.8, 22)); // dist
		addParallel(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new LiftPositionWait(false));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(.3, 3));
		addSequential(new OpenGripper());
		addSequential(new Wait(.4));
		addSequential(new DriveStraight(-.3, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		// addSequential(new SpinExactDegrees(60));
		// driveStraightSearchCube();
	}

	private void buildOppositeScaleFrom3() {
		addSequential(new DriveStraight(.8, 18.5));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new DriveStraight(.8, 17.5));
		addSequential(new SpinExactDegrees(90));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.25, 6.5));
		addSequential(new OpenGripper());
		addSequential(new Wait(.3));
		addSequential(new DriveStraight(-.25, 5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addSequential(new SpinExactDegrees(90));
		addParallel(new DriveStraight(.25, 100));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.3, 3));
		addSequential(new SpinDegrees(IMU.getInstance().getInitialHeading() - IMU.getInstance().getFusedHeading()));

		addSequential(new DriveStraight(.4, 6));
		addSequential(new OpenGripper());
		addSequential(new Wait(.3));
		addSequential(new DriveStraight(-.3, 5));
	}
}