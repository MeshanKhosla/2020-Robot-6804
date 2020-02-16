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
   private final Joystick ppStickTwo;
   
   private double forwardSpeed;


  public DriveCommand(Drivetrain driveTrain, Joystick driverControllerOne, Joystick driverControllerTwo) {
    // Use addRequirements() here to declare s
    m_Drivetrain = driveTrain;
    ppStickOne = driverControllerOne;
    ppStickTwo = driverControllerTwo;

    addRequirements(m_Drivetrain);
    

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if(Math.abs(-ppStickOne.getRawAxis(1)) > .05) {
      forwardSpeed = -ppStickOne.getRawAxis(1);
    }

    m_Drivetrain.teleop_Drive(forwardSpeed, ppStickOne.getRawAxis(2));

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
