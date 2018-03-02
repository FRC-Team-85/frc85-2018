package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;
import org.usfirst.frc.team85.robot.commands.drivetrain.PowerCurve;
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
			buildCloseScale();
			break;
		case "LRL":
			buildSwitch();
			break;
		case "RLR":
			addSequential(new PowerCurve(1.0, 90));
			break;
		case "RRR":
			buildOppositeScale();
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

	private void build1() {
		addSequential(new DriveStraight(1.0, 15));
		addSequential(new SpinExactDegrees(-90));

		addParallel(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(.8, 15));

		addSequential(new SpinExactDegrees(-90));
		addSequential(new DriveStraight(.5, 3));
		addSequential(new OpenGripper());
		addSequential(new Wait(.5));
		addSequential(new DriveStraight(-.5, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addSequential(new Wait(1));

		addParallel(new DriveStraight(.5, 6));
		addSequential(new CubeSearch());

		addSequential(new DriveStraight(-.5, 3));

		addParallel(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(.5, 3));
		addSequential(new OpenGripper());
	}

	private void driveStraightSearchCube() {
		addParallel(new DriveStraight(.25, 100));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(0, 0));
	}

	private void buildCloseScale() {
		addSequential(new DriveStraight(.8, 22)); // dist
		addParallel(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new LiftPositionWait(false));
		addSequential(new SpinExactDegrees(45));
		addSequential(new DriveStraight(.3, 3));
		addSequential(new OpenGripper());
		addSequential(new Wait(.4));
		addSequential(new DriveStraight(-.3, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addSequential(new SpinExactDegrees(60));
		driveStraightSearchCube();
	}

	private void buildSwitch() {
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(.5, 8.5)); // dist
		addSequential(new OpenGripper());
		addSequential(new Wait(.3));
		addSequential(new DriveStraight(-.3, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildOppositeScale() {
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