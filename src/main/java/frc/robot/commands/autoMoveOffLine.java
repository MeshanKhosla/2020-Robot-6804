/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class autoMoveOffLine extends CommandBase {
  /**
   * Creates a new autoMoveOffLine.
   */

  private Drivetrain m_drivetrain;
  private Timer moveTimer;
  
  public autoMoveOffLine(Drivetrain drivetrain) {
    m_drivetrain = drivetrain; 

    addRequirements(m_drivetrain);
    moveTimer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    moveTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(moveTimer.get() < 2) {
      m_drivetrain.regularArcadeDrive(.6, 0);
    }
    if(moveTimer.get() > 2) {
      m_drivetrain.regularArcadeDrive(0, 0);
      
    }

    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // 2 more than timer speed
    return moveTimer.get() > 4;
  }
}
