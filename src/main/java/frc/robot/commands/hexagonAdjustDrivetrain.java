/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class hexagonAdjustDrivetrain extends CommandBase {
  /**
   * Creates a new hexagonAdjustDrivetrain.
   * 
   * 
   */

   private final Drivetrain m_Drivetrain;
   private final Limelight m_Limelight; 
   private final PIDController limelightPID;


   

  //  private double m_steeringKP = 0.055;
  //  private double m_targetArea = 2.1;
  //  private double m_driveKP = 0.80; 
   private final double hexagonDesired = 0;
   

   private double hexagonMotorSpeed;


  public hexagonAdjustDrivetrain(Drivetrain driveTrain, Limelight limelight) {
    // Use addRequirements() here to declare subsystem dependencies.

   m_Drivetrain = driveTrain; 
   m_Limelight = limelight; 
   limelightPID = new PIDController(0.1,0.001,0);



   addRequirements(m_Drivetrain, m_Limelight);

  //  SmartDashboard.putNumber("steering KP", 0.055);
  //  SmartDashboard.putNumber("min Target Area", 2.1);
  //  SmartDashboard.putNumber("Driving KP", 0.80);





    

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Limelight.setPipeline(1);

    //initialize the hexagonAdjustDrivetrain
    // m_steeringKP =  SmartDashboard.getNumber("steering KP", 0.0);
    // m_targetArea = SmartDashboard.getNumber("min Target Area", 0.0);
    // m_driveKP =  SmartDashboard.getNumber("Driving KP", 0.0);

 

    

   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // double right = m_Limelight.getXOffset()*m_steeringKP;
    // double left = (m_targetArea - m_Limelight.getArea()) * m_driveKP;
    // SmartDashboard.putNumber("target area", m_Limelight.getArea());



    // SmartDashboard.putNumber("Left", left);
    // SmartDashboard.putNumber("Right", right);
    // if(m_Limelight.hasTarget()){
    //   if(m_Limelight.getArea() >= m_targetArea){
    //     left = 0;
    //     right = 0; 

    //   }
    //   else
    //   {
    //     left = 0;
    //     right = 0;
         
    //   }
      
     

    // }


    // m_Drivetrain.regularTankDrive(left,right);

    hexagonMotorSpeed = limelightPID.calculate(m_Limelight.getXOffset(), hexagonDesired);
    limelightPID.setTolerance(.5);
    m_Drivetrain.regularArcadeDrive(0, -hexagonMotorSpeed);
    //m_Drivetrain.teleop_Drive_tank(hexagonMotorSpeed, -hexagonMotorSpeed);

  }
  
  // public void simpleAdjust() {
  //   m_Drivetrain.teleop_Drive_tank(m_Limelight.getXOffset() * m_driveKP,-m_Limelight.getXOffset() * m_driveKP );
  // }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.regularTankDrive(0,0);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
