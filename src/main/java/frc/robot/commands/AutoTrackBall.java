/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class AutoTrackBall extends CommandBase {
  /**
   * Creates a new AutoTrackBall.
   */

  // Use addRequirements() here to declare subsystem dependencies.

  private final Intake m_ballTracker;
  private final Drivetrain m_driveTrain;


  public AutoTrackBall(Intake ballTracker, Drivetrain driveTrain) {
    m_ballTracker = ballTracker;
    m_driveTrain = driveTrain;

    addRequirements(m_ballTracker, m_driveTrain);

  }

  private Pixy2 ballTracker;
  private boolean isCamera = false;
  private int state = -1;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
 
    ballTracker = Pixy2.createInstance(Pixy2.LinkType.SPI);
    ballTracker.init(0);
    System.out.println(ballTracker.init(0));

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ballTracker.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 1);

    ArrayList<Block> blocks = ballTracker.getCCC().getBlocks();

    if (blocks.size() > 0) {
      double xCoord = blocks.get(0).getX();
      double yCoord = blocks.get(0).getY();
      String data = blocks.get(0).toString();
     

      // System.out.println("Intake ball present " + true);
      // System.out.println("Intake X coord " + xCoord);
      // System.out.println("Intake Y coord " + yCoord);
      // System.out.println("Intake Data" + data);
      // System.out.println("Intake Pixy Size " + blocks.size());


      // Robot oscilates to direction of ball for intake

      double kp = 0.25;
      double desired = 165;
      double current = xCoord;

      double error = (desired - current);
      double speed = (error * kp);

      SmartDashboard.putBoolean("IntakeBallPresent", true);
      SmartDashboard.putNumber("xcoord", xCoord);
      SmartDashboard.putNumber("ycoord", yCoord);
      SmartDashboard.putString("Data", data);
      SmartDashboard.putNumber("Intake Pixy Size", blocks.size());



       m_driveTrain.drive.tankDrive(-speed, speed);
      
      if(error > -5 || error < 5)
      {
      //m_driveTrain.drive.arcadeDrive(0.25, 0.0);
      error = 0; 


      }
        


      } else {
        SmartDashboard.putNumber("Intake Pixy Size", blocks.size());
        SmartDashboard.putBoolean("Intake Pixy detect ball", false);
        
        //System.out.println("Intake ball present " + false);
        //System.out.println("Intake Pixy Size " + blocks.size());
      }


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
