/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
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

  // Color sensor
  // private final I2C.Port i2cPort = I2C.Port.kOnboard;
  // private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

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
    
    // PS4 Controller  
    // Tank drive
    // m_Drivetrain.teleop_Drive_tank(ppStickOne);

    // Arcade drive
    m_Drivetrain.teleop_Drive_arcade(ppStickOne);

    // GTA Drive
    // m_Drivetrain.GTADrive(ppStickOne);

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
