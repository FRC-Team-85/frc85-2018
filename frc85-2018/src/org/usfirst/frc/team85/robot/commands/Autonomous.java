package org.usfirst.frc.team85.robot.commands;

import org.usfirst.frc.team85.robot.Variables;
import org.usfirst.frc.team85.robot.commands.drivetrain.DriveStraight;
import org.usfirst.frc.team85.robot.commands.drivetrain.SpinExactDegrees;
import org.usfirst.frc.team85.robot.commands.gripper.OpenGripper;
import org.usfirst.frc.team85.robot.commands.lift.LiftPositionWait;
import org.usfirst.frc.team85.robot.commands.lift.SetLiftHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

	public Autonomous() {

	}

	public void init(String fieldKey) {
		build1();
		build2();

		switch (fieldKey) {
		case "LLL":
			break;
		case "LRL":
			break;
		case "RLR":
			break;
		case "RRR":
			break;
		}
		start();
	}

	private void build1() {
		addSequential(new DriveStraight(1.0, 10));
		addSequential(new SpinExactDegrees(-90));

		addParallel(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(.8, 15));

		addSequential(new SpinExactDegrees(-90));
		addSequential(new DriveStraight(.5, 3));
		addSequential(new OpenGripper());
		addSequential(new Wait(.5));
		addSequential(new DriveStraight(-.5, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));

		addParallel(new DriveStraight(.5, 3));
		addSequential(new CubeSearch());

		addSequential(new DriveStraight(-.5, 3));

		addParallel(new SetLiftHeight(Variables.LIFT_SWITCH));
		addSequential(new DriveStraight(.5, 3));
		addSequential(new OpenGripper());
	}

	private void build2() {
		addSequential(new DriveStraight(1.0, 10));
		addSequential(new SpinExactDegrees(-90));
		addSequential(new DriveStraight(1.0, 10));
		addSequential(new SpinExactDegrees(90));
		addSequential(new SetLiftHeight(Variables.LIFT_SCALE_HIGH));
		addSequential(new LiftPositionWait(false));
		addSequential(new DriveStraight(.25, 3));
		addSequential(new OpenGripper());
		addSequential(new DriveStraight(-.25, 3));
		addSequential(new SetLiftHeight(Variables.LIFT_GROUND));
	}
}