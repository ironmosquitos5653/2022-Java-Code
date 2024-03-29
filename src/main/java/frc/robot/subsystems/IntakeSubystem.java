// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


import frc.robot.commands.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class IntakeSubystem extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    private static final int deviceID = 4;
    private CANSparkMax m_motor;
    private static final int otbdeviceID = 7;
    private CANSparkMax otb_motor;
    private RelativeEncoder otbEncoder;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private Servo servo;
private DigitalInput oTBLimitSwitch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public IntakeSubystem() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
servo = new Servo(2);
 addChild("Servo", servo);
 

oTBLimitSwitch = new DigitalInput(2);
 addChild("OTBLimitSwitch", oTBLimitSwitch);
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        m_motor = new CANSparkMax(deviceID, MotorType.kBrushless);
        m_motor.restoreFactoryDefaults();
        otb_motor = new CANSparkMax(otbdeviceID, MotorType.kBrushless);
        otb_motor.restoreFactoryDefaults();
        otbEncoder = otb_motor.getEncoder();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static final double DEFAULT_SPEED = 1;
    boolean isOn = false;
    double speed = DEFAULT_SPEED;
    public void toggle() {
        speed = DEFAULT_SPEED;
        if(isOn) {
            m_motor.set(0);
        }
        else{
            m_motor.set(speed);
        
        }
        isOn = ! isOn;
        SmartDashboard.putNumber("Intake Speed", m_motor.get());
    }
    public void reverse() {
        m_motor.set(-speed);
    }
    public void stop() {
        m_motor.set(0);
        isOn = false;

    }
    public void start() {
        m_motor.set(speed);

    }
    public void reverseAdvanceOn(boolean isOn) {
        
        if(isOn) {
           m_motor.set(-.3);
           
        }
        else{
           m_motor.set(0);
            
        
        }
        SmartDashboard.putNumber("advance speed", m_motor.get());
    }
    public double getOtbPosition() {
        return otbEncoder.getPosition();
    }
    public void resetEncoder() {
       otbEncoder.setPosition(0);
    }
    public boolean intakeUp = true; 

    public void otbOn() {
        double otbSpeed = 1;
        if(! intakeUp) {
            otbSpeed = -otbSpeed;
        }
        otb_motor.set(otbSpeed);
        
    }
    public void otbStop() {
        otb_motor.set(0);
        intakeUp = !intakeUp;
    }

    public void setAngle(double angle) {
        servo.setAngle(angle);
    }
    public boolean getOtbSwitch() {
        return oTBLimitSwitch.get();
    }
}

