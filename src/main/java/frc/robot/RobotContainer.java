/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;


public class RobotContainer {
  // Subsystems
  private final Drivetrain driveSubsystem = new Drivetrain();
  private final Joystick m_driver_controller = new Joystick(0);
  private final DriveCommand driveCommand = new DriveCommand(driveSubsystem, m_driver_controller);

  

  // Joysticks
  Joystick driveStick = new Joystick(1);
  
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();


    driveSubsystem.setDefaultCommand(driveCommand);
  }

  
  private void configureButtonBindings() {

    
  }



}
