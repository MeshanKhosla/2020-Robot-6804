/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */

  private WPI_TalonSRX masterLeft;
  private WPI_TalonSRX masterRight;
  private WPI_TalonSRX slaveLeft;
  private WPI_TalonSRX slaveRight;

  
  private DifferentialDrive drive; 

  

  
  public Drivetrain() {
    
    masterLeft = new WPI_TalonSRX(1);
    masterRight = new WPI_TalonSRX(5);
    slaveLeft = new WPI_TalonSRX(2);
    slaveRight = new WPI_TalonSRX(4);
    
   

    slaveLeft.follow(masterLeft);
    slaveRight.follow(masterRight);
    masterRight.setInverted(false);
    masterLeft.setInverted(false);

  drive = new DifferentialDrive(masterLeft, masterRight);

  
  }
public DifferentialDrive getDrive() {
  return drive;
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}