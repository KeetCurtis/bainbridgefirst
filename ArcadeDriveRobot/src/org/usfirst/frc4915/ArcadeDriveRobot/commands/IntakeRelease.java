// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc4915.ArcadeDriveRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.ArcadeDriveRobot.Robot;
/**
 * TODO: Finish code!
 */
public class  IntakeRelease extends Command {
    public IntakeRelease() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.harvester);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
         // runs the motors for at least 2.0 seconds
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(2.0);
        System.out.println("Running ReleaseBall...");
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.harvester.setWheelSpeed(0.50);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();    /*!Robot.harvester.getLimitSwitchBallLoaded()*/
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.harvester.stopWheels();
        System.out.println("ReleaseBall done.");
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
