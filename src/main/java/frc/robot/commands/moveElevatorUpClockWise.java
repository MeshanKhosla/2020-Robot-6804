/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class moveElevatorUpClockWise extends CommandBase {
  /**
   * Creates a new moveElevatorUp.
   */
  private Elevator m_elevatorSubsystem;

  public moveElevatorUpClockWise(Elevator elevatorSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_elevatorSubsystem = elevatorSubsystem;
    addRequirements(elevatorSubsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
      First parameter: speed of motor that brings elevator up Bottom
      Second parameter: speed of motor that brings elevator down (climbs) (80:1)
    */

    // Square
    // L2 bottom spools clockwise
    m_elevatorSubsystem.setElevatorSpeed(.6, 0);

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
