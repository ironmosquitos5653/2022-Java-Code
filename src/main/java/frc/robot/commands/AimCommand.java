// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.hal.simulation.RoboRioDataJNI;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class AimCommand extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private static final int BLUE_BALL_PIPELINE = 0;
    private static final int RED_BALL_PIPELINE = 1; 
    private static final int HUB_PIPELINE = 2; 
    private boolean aimBall;
    private DrivetrainSubsystem m_drivetrainSubsystem;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public AimCommand(boolean aimBall, DrivetrainSubsystem subsystem) {
        this.aimBall = aimBall;
        m_drivetrainSubsystem = subsystem;
        addRequirements(m_drivetrainSubsystem);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // m_subsystem = subsystem;
        // addRequirements(m_subsystem);    

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    public double angle;
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Timer tmr = new Timer();
        tmr.start();
        table = NetworkTableInstance.getDefault().getTable("limelight");
        if(!aimBall) {
            table.getEntry("pipeline").setNumber(HUB_PIPELINE);
        }
        else if (false || DriverStation.getAlliance() == DriverStation.Alliance.Red) {
            table.getEntry("pipeline").setNumber(RED_BALL_PIPELINE);
            
        }
        else {
            table.getEntry("pipeline").setNumber(BLUE_BALL_PIPELINE);

        }
        // table.getEntry("ledMode").setNumber(3);
        while(tmr.hasElapsed(.25)){}
        
          tx = table.getEntry("tx");
    }
    double x;
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        x = tx.getDouble(0.0);

        SmartDashboard.putNumber("LimelightX", x);

        if (x > 0) {
            m_drivetrainSubsystem.rotate(true);
        }
        else{
            m_drivetrainSubsystem.rotate(false);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RobotContainer.getInstance().m_drivetrainSubsystem.setspeed(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        double threshold = 3;
        return x < threshold && x > -threshold;
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
