/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveCommand extends CommandBase {
  /**
   * Creates a new DriveCommand.
   * 
   * 
   */


   private final Drivetrain m_Drivetrain;
   private final Joystick ppStickOne;
   
  //  Arcade drive
   private double forwardSpeed;
   private double turningSpeed;

  //  Tank drive
   private double leftSpeed;
   private double rightSpeed;

  //  Deadband
   private final double deadband = .05;


  public DriveCommand(Drivetrain driveTrain, Joystick driverControllerOne) {
    // Use addRequirements() here to declare s
    m_Drivetrain = driveTrain;
    ppStickOne = driverControllerOne;
    addRequirements(m_Drivetrain);
    

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Thrustmaster
    // if(Math.abs(-ppStickOne.getRawAxis(1)) <= deadband) {
    //   forwardSpeed = 0;
    // } else {
    //   forwardSpeed = -ppStickOne.getRawAxis(1);
    // }

    // if(Math.abs(ppStickOne.getRawAxis(2)) <= deadband) {
    //   turningSpeed = 0;
    // } else {
    //   turningSpeed = ppStickOne.getRawAxis(2);
    // } 
    // m_Drivetrain.teleop_Drive_arcade(forwardSpeed, turningSpeed);

    // PS4 Controller  
    // left deadband
    if(Math.abs(-ppStickOne.getRawAxis(1)) <= deadband) {
      leftSpeed = 0;
    } else {
      leftSpeed = -ppStickOne.getRawAxis(1);
    }
    // Right deadband
    if(Math.abs(-ppStickOne.getRawAxis(5)) <= deadband) {
      rightSpeed = 0;
    } else {
      rightSpeed = -ppStickOne.getRawAxis(5);
    }

    m_Drivetrain.teleop_Drive_tank(leftSpeed, rightSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
