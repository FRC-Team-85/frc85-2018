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

	public Autonomous(String fieldKey) {
		switch (fieldKey) {
		case "LLL":
			buildCloseScaleFrom3();
			break;
		case "LRL":
			buildCloseSwitchFrom3();
			break;
		case "RLR":
			buildFarSwitchFrom3();
			break;
		case "RRR":
			buildFarScaleFrom3();
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

	private void buildCloseSwitchFrom3() {
		addSequential(new DriveStraight(1.0, 12));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new Wait(.3));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.5, 4));
		addSequential(new OpenGripper());
		addSequential(new Wait(.2));
		addSequential(new DriveStraight(-.5, 4));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addSequential(new SpinExactDegrees(90));
		addSequential(new DriveStraight(1.0, 6.5));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new DriveStraight(.8, 4, AbsoluteDirection.RIGHT));
		addSequential(new SpinExactDegrees(-90));
		addParallel(new DriveStraight(.8, 5));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.8, 3));
	}

	private void buildFarSwitchFrom3() {
		addSequential(new DriveStraight(1.0, 18.5));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new DriveStraight(1.0, 17.5, AbsoluteDirection.RIGHT));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new SetLiftHeight(Variables.LIFT_SWITCH));
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
		addSequential(new SpinExactDegrees(-45));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.6, 2.5));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.6, 2.5));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new LiftPositionWait(false));

		addParallel(new DriveStraight(.8, 8));
		addSequential(new CubeSearch());
		addSequential(new DriveStraight(-.8, 4));

		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new SpinExactDegrees(135));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.8, 1.5));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.8, 3));
		addSequential(new SpinExactDegrees(-135));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}

	private void buildFarScaleFrom3() {
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
}