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
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class ShootCommand extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private boolean m_low;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    private Timer timer = null;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public ShootCommand(boolean low) {


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_low = low;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // m_subsystem = subsystem;
        // addRequirements(m_subsystem);    

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer = new Timer();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(timer.hasElapsed(.1))
            RobotContainer.getInstance().m_shooterSubsystem.shooterOn(true, m_low);
        if(!timer.hasElapsed(.3)){
            RobotContainer.getInstance().m_shooterSubsystem.reverseAdvanceOn(true);
            RobotContainer.getInstance().m_intakeSubystem.reverseAdvanceOn(true);
        } else if (timer.hasElapsed(.3) && !timer.hasElapsed(.7)) {
            RobotContainer.getInstance().m_shooterSubsystem.reverseAdvanceOn(false);
            RobotContainer.getInstance().m_intakeSubystem.reverseAdvanceOn(false);
    }
        if(timer.hasElapsed(.7)) {
           RobotContainer.getInstance().m_shooterSubsystem.advanceOn(true);
           RobotContainer.getInstance().m_intakeSubystem.start();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RobotContainer.getInstance().m_shooterSubsystem.shooterOn(false, m_low);
        RobotContainer.getInstance().m_shooterSubsystem.advanceOn(false);
        RobotContainer.getInstance().m_intakeSubystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.hasElapsed(3);
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
