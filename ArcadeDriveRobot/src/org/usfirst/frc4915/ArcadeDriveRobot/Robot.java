// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc4915.ArcadeDriveRobot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4915.ArcadeDriveRobot.commands.*;
import org.usfirst.frc4915.ArcadeDriveRobot.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    Command autonomousCommand;
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Harvester harvester;
    public static AirCompressor airCompressor;
    public static Launcher launcher;
    public static Gyroscope gyroscope;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static final String VERSION = "v1.07.06";
    // Adds IntakeDown and IntakeUp commands
    // Adds Magnetic Switch
    // Changed buttons on Joystick to activate the Intake Down and Intake Up instead of Extend/Retract Pneumatics
    // Fixed exceptions when there is no gyroscope
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        harvester = new Harvester();
        airCompressor = new AirCompressor();
        launcher = new Launcher();
        gyroscope = new Gyroscope();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        System.out.println(VERSION);
        SendUserMessages.display(1, VERSION); //tests the SendUserMessages, 30 characters
        SendUserMessages.display(2, "Inverted both motors"); //tests the SendUserMessages, 30 characters
        if (gyroscope != null) {
            gyroscope.reset();
        }
        if (airCompressor != null) {
            airCompressor.start();
        }
        driveTrain.setSafetyEnabled(false);
    }
    
    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) {
            autonomousCommand.start();
        }
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        SmartDashboard.putBoolean("Pressure Switch", RobotMap.airCompressorCompressor.getPressureSwitchValue());
        // Makes our throttle from the original [-1,1] to [.1,1]
        // SmartDashboard.putNumber("Z Axis ", oi.joystickDrive.getAxis(Joystick.AxisType.kZ)); // Attack Joystick Throttle
        
        SmartDashboard.putBoolean("Gearbox Pneumatics", launcher.getStatePneumatics());
        
        SmartDashboard.putBoolean("Harvester Limit Switch is Ball Loaded: ", harvester.getLimitSwitchBallLoaded());
        SmartDashboard.putBoolean("Harvester Intake down", !harvester.getMagneticSwitchPneumatics()); // fully extended is false -> true
        SmartDashboard.putBoolean("Harvester Intake up", harvester.getMagneticSwitchPneumatics()); // fully retraced is true -> true
        //SmartDashboard.putNumber("Gyroscope", gyroscope.getAngle());
        driveTrain.setSafetyEnabled(false);
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
