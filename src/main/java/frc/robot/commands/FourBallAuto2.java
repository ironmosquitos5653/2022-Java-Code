// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FourBallAuto2 extends SequentialCommandGroup {
  /** Creates a new FourBallAuto2. */
  public FourBallAuto2(DrivetrainSubsystem drivetrainSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new TwoBallAutoCommand(drivetrainSubsystem),
        new IntakeCommand(),
        new SetCameraAngle(0),
        new RotateCommand(23, drivetrainSubsystem),
        new DriveDistanceCommand(-186, drivetrainSubsystem),
        new DriveDistanceCommand(6, drivetrainSubsystem),
        new WaitCommand(),
        new RotateCommand(-15, drivetrainSubsystem),
        new DriveDistanceCommand(151, drivetrainSubsystem),
        new ShootCommand(false)

    );
  }
}
