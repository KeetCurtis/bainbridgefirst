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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.ArcadeDriveRobot.Robot;
/**
 *
 */
public class  Turn180 extends Command {
    
    //private double degreesOfFreedom = 2.0;
    
    private boolean shouldQuit;
    
    public Turn180() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        /*
        if (Robot.gyroscope == null) {
            end();
        }
        Robot.gyroscope.reset();
        // set the max output of the motors
        Robot.driveTrain.setMaxOutput(0.5);
        // set safety timeout
        setTimeout(6.0);
        */
        setTimeout(5.0);
        System.out.println("Began 180 turn");
        shouldQuit = false;
        Robot.driveTrain.resetPIDValues();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("Turning");
        shouldQuit = Robot.driveTrain.turnPID(180.0);
        //Robot.driveTrain.turn();
    }
    // Checks whether Robot has turned within a certain number of degrees from 180
    protected boolean isFinished() {
        //return ((180.0-degreesOfFreedom < Robot.gyroscope.getAngle()) ||
        //     (-180.0+degreesOfFreedom > Robot.gyroscope.getAngle()) || isTimedOut());
        if (isTimedOut()) {
            return true;
        }
        else {
            return shouldQuit;
        }
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.stop();
        System.out.println("Finished Turning");
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    // Can't be interrupted
    protected void interrupted() {
        end();
    }
}
