/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drivetrain;


public class RobotContainer {
  // Subsystems
  private final Drivetrain driveSubsystem = new Drivetrain();

  // Joysticks
  Joystick driveStick = new Joystick(1);
  
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();


    driveSubsystem.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new RunCommand(() -> driveSubsystem
            .arcadeDrive(driveStick.getRawAxis(1),
                         driveStick.getRawAxis(3))));
  }

  
  private void configureButtonBindings() {

    
  }



}
