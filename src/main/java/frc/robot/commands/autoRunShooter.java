/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class autoRunShooter extends CommandBase {
   /**
   * Creates a new Shoot.
   */
  
  private Timer shooterTimer;
  
  Shooter m_shooterSubsystem;
  public autoRunShooter(Shooter shooterSubsystem) {

    m_shooterSubsystem = shooterSubsystem;

    addRequirements(m_shooterSubsystem);
    shooterTimer = new Timer();
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    shooterTimer.start();



  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooterSubsystem.setShooterSpeed(.75);

  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shooterTimer.get() > 2;
    
  }
}
