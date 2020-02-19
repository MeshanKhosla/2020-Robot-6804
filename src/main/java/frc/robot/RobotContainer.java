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
import frc.robot.commands.runBeltDown;
import frc.robot.commands.runBeltUp;
import frc.robot.commands.shooterStop;
import frc.robot.commands.stopBelt;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;


public class RobotContainer {
  // Subsystems
  private final Drivetrain driveSubsystem = new Drivetrain();
  private final Intake intakeSubsystem = new Intake();
  private final Shooter shooterSubsystem = new Shooter();
  private final Belt beltSubsystem = new Belt();
  // Joysticks
  private final Joystick upDownController = new Joystick(1);

// Drive command
  private final DriveCommand driveCommand = new DriveCommand(driveSubsystem, upDownController);
  


  //sendable choooser 
  //sends options to either smartdashboard/shuffleboard for autonomous commands 


  
  




  public RobotContainer() 
  {
    // Configure the button bindings
    configureButtonBindings();  

    //sendable chooser
    // chooser = new SendableChooser<Command>();
    // chooser.setDefaultOption("Stop Shooter", stopShooterCommand);
    // chooser.addOption("Shoot", shootCommand );
    
    // SmartDashboard.putData("Autonomous Chooser", chooser);



  }



  // public void publishToSmartDashboard(){
  //   intakeSubsystem.publishToSmartDashboard();
  //   shooterSubsystem.publishToSmartDashboard();
    
  // }



  // Joystick buttons
  private final JoystickButton intakeInButton = new JoystickButton(upDownController, 3);
  private final JoystickButton intakeStopButton = new JoystickButton(upDownController, 2);
  private final JoystickButton shooterRunButton = new JoystickButton(upDownController, 14);
  private final JoystickButton shooterStopButton = new JoystickButton(upDownController, 15);
  private final JoystickButton beltUpButton = new JoystickButton(upDownController, 8);
  private final JoystickButton beltDownButton = new JoystickButton(upDownController, 9);
  private final JoystickButton beltStopButton = new JoystickButton(upDownController, 10);

  

  

  private void configureButtonBindings() 
  {
    driveSubsystem.setDefaultCommand(driveCommand);

    // Intake
    intakeInButton.whenHeld(new intakeIn(intakeSubsystem));
    intakeStopButton.whenPressed(new intakeStop(intakeSubsystem));
    

    // Shooter
    shooterRunButton.whenHeld(new Shoot(shooterSubsystem));
    shooterStopButton.whenPressed(new shooterStop(shooterSubsystem));

    // Belt 
    beltUpButton.whenHeld(new runBeltUp(beltSubsystem));
    beltDownButton.whenHeld(new runBeltDown(beltSubsystem));
    beltStopButton.whenPressed(new stopBelt(beltSubsystem));
  }



}
