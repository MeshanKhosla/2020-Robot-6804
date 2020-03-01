/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoTrackBall;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.Shoot;
import frc.robot.commands.hexagonAdjustDrivetrain;
import frc.robot.commands.hexagonAdjustYValue;
import frc.robot.commands.intakeIn;
import frc.robot.commands.intakeReverse;
import frc.robot.commands.intakeStop;
import frc.robot.commands.runBeltDown;
import frc.robot.commands.runBeltUp;
import frc.robot.commands.runBeltUpWithButton;
import frc.robot.commands.shooterStop;
import frc.robot.commands.stopBelt;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;


public class RobotContainer {
  // Subsystems
  private final Drivetrain driveSubsystem = new Drivetrain();
  private final Intake intakeSubsystem = new Intake();
  private final Shooter shooterSubsystem = new Shooter();
  private final Belt beltSubsystem = new Belt();
  private final Limelight limelightSubsystem = new Limelight();
  // Joysticks
  private final Joystick upDownController = new Joystick(1);
  private final Joystick ps4Controller = new Joystick(5);

// Drive command
  private final DriveCommand driveCommand = new DriveCommand(driveSubsystem, ps4Controller);
  private final runBeltUp beltUpCommand = new runBeltUp(beltSubsystem);

  public RobotContainer() 
  {
    // Configure the button bindings
    configureButtonBindings();  

    // Auto options
    addAutoOptions();
    
  }

  // Auto chooser
  private SendableChooser<Command> autoChooser = new SendableChooser<Command>();
  public void addAutoOptions() {
    
    autoChooser.setDefaultOption("Don't shoot", new shooterStop(shooterSubsystem));
    autoChooser.addOption("Shoot", new Shoot(shooterSubsystem));

    SmartDashboard.putData(autoChooser);
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  // Joystick buttons
  private final JoystickButton intakeInButton = new JoystickButton(upDownController, 3);
  private final JoystickButton intakeStopButton = new JoystickButton(upDownController, 2);
  private final JoystickButton intakeReverseButton = new JoystickButton(upDownController, 4);
  private final JoystickButton shooterRunButton = new JoystickButton(upDownController, 14);
  private final JoystickButton shooterStopButton = new JoystickButton(upDownController, 15);
  private final JoystickButton beltUpAutoButton = new JoystickButton(upDownController, 7);
  private final JoystickButton beltUpButton = new JoystickButton(upDownController, 8);
  private final JoystickButton beltDownButton = new JoystickButton(upDownController, 9);
  private final JoystickButton beltStopButton = new JoystickButton(upDownController, 10);
  private final JoystickButton limelightAdjustButton = new JoystickButton(upDownController, 1);
  private final JoystickButton limelightAdjustYValueButton = new JoystickButton(upDownController, 5);
  private final JoystickButton ballTrackButton = new JoystickButton(ps4Controller, 2);
  

  
  private void configureButtonBindings() 
  {
    driveSubsystem.setDefaultCommand(driveCommand);


    // Intake
    intakeInButton.whenPressed(new intakeIn(intakeSubsystem));
    intakeStopButton.whenPressed(new intakeStop(intakeSubsystem));
    intakeReverseButton.whenPressed(new intakeReverse(intakeSubsystem));

    ballTrackButton.whenHeld(new AutoTrackBall(intakeSubsystem, driveSubsystem));
    

    // Shooter
    shooterRunButton.whenPressed(new Shoot(shooterSubsystem));
    shooterStopButton.whenPressed(new shooterStop(shooterSubsystem));

    // Belt 
    beltSubsystem.setDefaultCommand(beltUpCommand);
    //beltUpAutoButton.whenPressed(new runBeltUp(beltSubsystem));
    beltUpButton.whenHeld(new runBeltUpWithButton(beltSubsystem));
    beltDownButton.whenHeld(new runBeltDown(beltSubsystem));
    beltStopButton.whenPressed(new stopBelt(beltSubsystem));

    // Limelight
    // limelightAdjustButton.whenHeld(new hexagonAdjustDrivetrain(driveSubsystem, limelightSubsystem));
    // limelightAdjustYValueButton.whenHeld(new hexagonAdjustYValue(driveSubsystem, limelightSubsystem));
  }



}
