/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
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

  private final Intake m_intake;
  private Drivetrain m_driveTrain;
  private PIDController pixyPID; 
  private double trackSpeed;
  private final double desired = 150;
  private Pixy2 ballTracker;


  public AutoTrackBall(Intake intake, Drivetrain driveTrain) {
    m_intake = intake;
    m_driveTrain = driveTrain;




    addRequirements(m_intake, m_driveTrain);
    pixyPID = new PIDController(0.02,0.001,0.00001);
    ballTracker = Pixy2.createInstance(Pixy2.LinkType.I2C);
    ballTracker.init(0);
    

  }



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
 
    
    //System.out.println(ballTracker.init(0));

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    ArrayList<Block> intakeblocks = ballTracker.getCCC().getBlocks();
    ballTracker.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 1);
    if (intakeblocks.size() > 0) {
    SmartDashboard.putBoolean("IntakeBallPresent", true);
    double ballTrackerXCoord = intakeblocks.get(0).getX();
    double ballTrackeryCoord = intakeblocks.get(0).getY();
    String ballTrackerdata = intakeblocks.get(0).toString();

      SmartDashboard.putNumber("Intake xcoord", ballTrackerXCoord);
      SmartDashboard.putNumber("Intake ycoord", ballTrackeryCoord);
      SmartDashboard.putString("Intake Data", ballTrackerdata);
      SmartDashboard.putNumber("Intake Pixy Size", intakeblocks.size());
      //System.out.println("Intake ball present " + false);
      //System.out.println("Intake Pixy Size " + intakeblocks.size());

      // Robot oscilates to direction of ball for intake
      trackSpeed = pixyPID.calculate(ballTrackerXCoord, desired);
      pixyPID.setTolerance(5);
      

      m_driveTrain.regularArcadeDrive(0, -trackSpeed);
      }
        
     else {
        SmartDashboard.putBoolean("IntakeBallPresent", false);

        m_driveTrain.regularArcadeDrive(0, 0);
       
        // System.out.println("Intake ball present " + false);
        // System.out.println("Intake Pixy Size " + intakeblocks.size());
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
