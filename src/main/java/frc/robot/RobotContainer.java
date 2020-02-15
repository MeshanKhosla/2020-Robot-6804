/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.Shoot;
import frc.robot.commands.intakeIn;
import frc.robot.commands.intakeStop;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;


public class RobotContainer {
  // Subsystems
  private final Drivetrain driveSubsystem = new Drivetrain();
  private final Intake intakeSubsystem = new Intake();
  private final Shooter shooterSubsystem = new Shooter();

  // Joysticks
  private final Joystick upDownController = new Joystick(1);
  private final Joystick turnController = new Joystick(2);


  // upDownController controls forward and backwards movement on the robot (parameter 1 on arcade drive)
  // sideController controls turning (parameter 2 on arcade drive)
  private final DriveCommand driveCommand = new DriveCommand(driveSubsystem, upDownController, turnController);
  private final Shoot shootCommand = new Shoot(shooterSubsystem, upDownController);

  
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();


    
  }

  // Joystick buttons
  private final JoystickButton intakeInButton = new JoystickButton(upDownController, 3);
  private final JoystickButton intakeStopButton = new JoystickButton(upDownController, 2);
  

  private void configureButtonBindings() {
    driveSubsystem.setDefaultCommand(driveCommand);
    shooterSubsystem.setDefaultCommand(shootCommand);
    intakeInButton.whenHeld(new intakeIn(intakeSubsystem));
    intakeStopButton.whenPressed(new intakeStop(intakeSubsystem));
  }



}
