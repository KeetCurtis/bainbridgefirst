// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc4915.ArcadeDriveRobot.subsystems;
import org.usfirst.frc4915.ArcadeDriveRobot.RobotMap;
import org.usfirst.frc4915.ArcadeDriveRobot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4915.ArcadeDriveRobot.Robot;
/**
 *
 */
public class DriveTrain extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController leftMotor = RobotMap.driveTrainLeftMotor;
    SpeedController rightMotor = RobotMap.driveTrainRightMotor;
    RobotDrive robotDrive = RobotMap.driveTrainRobotDrive;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private double joystickThrottle;
    private double modifiedThrottle;
    private boolean hasTurned;
    
    public DriveTrain() {
        joystickThrottle = modifyThrottle();
    }

    // Whether robot is turning or not
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new ArcadeDrive());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
     
    /** arcadeDrive
     * 
     * Public method to enable arcade drive using a joystick.
     * v 1.03.01
     * @param stick A joystick.
     */
    public void arcadeDrive(Joystick stick) {
        //TODO: TEST!
        joystickThrottle = modifyThrottle();
        robotDrive.arcadeDrive(stick);
    }
    
    /** driveStraight
     * 
     * Public method to drive straight at speed
     * v 1.03.01
     * @param speed Motor speed. Value should be between -1 and 1
     */
    
    public void driveStraight(double speed) {
        robotDrive.tankDrive(speed, speed);
    }
    
    /** stop
     * 
     * public method to stop the drive train
     * v 1.03.01
     */
    public void stop() {
        robotDrive.tankDrive(0, 0);
    }
    /* turn
     *
     * Turns robot specified angle
    */
    public void turn(double angle) {
        if (Robot.gyroscope != null) {
            hasTurned = false;
            double degreesOfFreedom = 2.0;
            Robot.gyroscope.reset();
            while (Robot.gyroscope.getAngle() <= angle - degreesOfFreedom) {
                robotDrive.tankDrive(0.5, -0.5);
            }
            stop();
            hasTurned = true;
        } else {
            System.out.println("No Gyro");
        }
    }
    
    public boolean isTurned() {
        return hasTurned;
    }
    
    public double modifyThrottle() {
        modifiedThrottle = 0.45 * (-1.0 * Robot.oi.joystickDrive.getAxis(Joystick.AxisType.kThrottle)) + 0.55;
        if (modifiedThrottle != joystickThrottle) {
            SmartDashboard.putNumber("Throttle: ", modifiedThrottle);
        }
        setMaxOutput(modifiedThrottle);
        return modifiedThrottle;
    }
   
    public void setMaxOutput(double topSpeed) {
        robotDrive.setMaxOutput(topSpeed);
    }
    
    public void setSafetyEnabled(boolean safety) {
        robotDrive.setSafetyEnabled(safety);
    }
}
