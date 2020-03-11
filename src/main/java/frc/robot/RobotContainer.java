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
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoTrackBall;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.Shoot;
import frc.robot.commands.autoMoveOffLine;
import frc.robot.commands.autoRunShooter;
import frc.robot.commands.hexagonAdjustDrivetrain;
import frc.robot.commands.hexagonAdjustYValue;
import frc.robot.commands.intakeIn;
import frc.robot.commands.intakeReverse;
import frc.robot.commands.intakeStop;
import frc.robot.commands.moveElevatorDown;
import frc.robot.commands.moveElevatorUp;
import frc.robot.commands.moveElevatorUpClockWise;
import frc.robot.commands.moveElevatorDownClockWise;
import frc.robot.commands.runBeltDown;
import frc.robot.commands.runBeltUp;
import frc.robot.commands.runBeltUpWithButton;
import frc.robot.commands.setLEDColor;
import frc.robot.commands.shooterStop;
import frc.robot.commands.stopBelt;
import frc.robot.commands.stopElevator;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;


public class RobotContainer {
  // Subsystems
  private final Drivetrain driveSubsystem = new Drivetrain();
  private final Intake intakeSubsystem = new Intake();
  private final Shooter shooterSubsystem = new Shooter();
  private final Belt beltSubsystem = new Belt();
  private final Limelight limelightSubsystem = new Limelight();
  private final Elevator elevatorSubsystem = new Elevator();
  private final LEDs ledSubsystem = new LEDs();
  // Joysticks
  private final Joystick buttonBoard = new Joystick(4);
  private final Joystick ps4Controller = new Joystick(5);
  private final Joystick buttonBoardTwo = new Joystick(1);

// Drive command
  private final DriveCommand driveCommand = new DriveCommand(driveSubsystem, ps4Controller);
  private final runBeltUp beltUpCommand = new runBeltUp(beltSubsystem);
  private final AutoTrackBall trackBallCommand = new AutoTrackBall(intakeSubsystem, driveSubsystem);

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
    
    autoChooser.setDefaultOption("Don't move", new shooterStop(shooterSubsystem));
    //autoChooser.addOption("Move", new autoMoveOffLine(driveSubsystem).andThen(new Shoot(shooterSubsystem)));
    
    autoChooser.addOption("Move", new autoMoveOffLine(driveSubsystem).andThen(new autoRunShooter(shooterSubsystem)).andThen(new runBeltUpWithButton(beltSubsystem)));
    //autoChooser.addOption("Move", new autoRunShooter(shooterSubsystem).andThen(new runBeltUpWithButton(beltSubsystem)));

    SmartDashboard.putData(autoChooser);
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  // Joystick buttons
  private final JoystickButton intakeInButton = new JoystickButton(buttonBoard, 1);
  private final JoystickButton intakeReverseButton = new JoystickButton(buttonBoard, 2);
  private final JoystickButton intakeStopButton = new JoystickButton(buttonBoard, 3);

  private final JoystickButton beltUpButton = new JoystickButton(buttonBoard, 4);
  private final JoystickButton beltDownButton = new JoystickButton(buttonBoard, 5);
  private final JoystickButton beltStopButton = new JoystickButton(buttonBoard, 6);

  private final JoystickButton shooterRunButton = new JoystickButton(buttonBoard, 7);
  private final JoystickButton shooterStopButton = new JoystickButton(buttonBoard, 8);

  private final JoystickButton limelightAdjustButton = new JoystickButton(ps4Controller, 8);
  private final JoystickButton limelightAdjustYValueButton = new JoystickButton(ps4Controller, 7);

  private final JoystickButton ballTrackButton = new JoystickButton(ps4Controller, 2);

  private final JoystickButton elevatorDownButton = new JoystickButton(buttonBoard, 9);
  private final JoystickButton elevatorUpButton = new JoystickButton(buttonBoard, 10);
  private final JoystickButton elevatorUpClockWiseButton = new JoystickButton(buttonBoard, 11);
  private final JoystickButton elevatorDownClockWiseButton = new JoystickButton(buttonBoard, 12);
  private final JoystickButton elevatorStopButton = new JoystickButton(buttonBoardTwo, 12);
  


  

  
  private void configureButtonBindings() 
  {
    driveSubsystem.setDefaultCommand(driveCommand);
    ballTrackButton.whenHeld(new AutoTrackBall(intakeSubsystem, driveSubsystem));

    // Intake
    intakeInButton.whenPressed(new intakeIn(intakeSubsystem));
    intakeStopButton.whenPressed(new intakeStop(intakeSubsystem));
    intakeReverseButton.whenPressed(new intakeReverse(intakeSubsystem));

    
    

    // Shooter
    shooterRunButton.whenPressed(new Shoot(shooterSubsystem));
    shooterStopButton.whenPressed(new shooterStop(shooterSubsystem));

    // Belt 
    //beltSubsystem.setDefaultCommand(beltUpCommand);
    //beltUpAutoButton.whenPressed(new runBeltUp(beltSubsystem));
    beltUpButton.whenHeld(new runBeltUpWithButton(beltSubsystem));
    beltDownButton.whenHeld(new runBeltDown(beltSubsystem));
    beltStopButton.whenPressed(new stopBelt(beltSubsystem));

    // Limelight
    limelightAdjustButton.whenHeld(new hexagonAdjustDrivetrain(driveSubsystem, limelightSubsystem));
    // limelightAdjustYValueButton.whenHeld(new hexagonAdjustYValue(driveSubsystem, limelightSubsystem));

    // Elevator 
    elevatorUpButton.whenHeld(new moveElevatorUp(elevatorSubsystem));
    elevatorDownButton.whenHeld(new moveElevatorDown(elevatorSubsystem));
    elevatorStopButton.whenPressed(new stopElevator(elevatorSubsystem));

    //clockwise elevator
    elevatorUpClockWiseButton.whenHeld(new moveElevatorUpClockWise(elevatorSubsystem));
    elevatorDownClockWiseButton.whenHeld(new moveElevatorDownClockWise(elevatorSubsystem));
    limelightAdjustYValueButton.whenHeld(new hexagonAdjustYValue(driveSubsystem, limelightSubsystem));
    //LED
    //ledSubsystem.setDefaultCommand(new setLEDColor());

  }

}
