/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
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
  
  public DifferentialDrive drive; 

  private double deadband = 0.05; 
  private double forwardSpeed;
  private double turningSpeed;
  private double leftSpeed;
  private double rightSpeed;
  

  
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

public void teleop_Drive_arcade(Joystick stick) {
      // forward deadband
      if(Math.abs(-stick.getRawAxis(1)) <= deadband) {
        forwardSpeed = 0;
      } else {
        forwardSpeed = -stick.getRawAxis(1);
      }
      // turning deadband
      if(Math.abs(stick.getRawAxis(2)) <= deadband) {
        turningSpeed = 0;
      } else {
        turningSpeed = stick.getRawAxis(2);
      }
     drive.arcadeDrive(forwardSpeed, turningSpeed);
}

public void teleop_Drive_tank(Joystick stick) {
    // Tank drive
    // left deadband
    if(Math.abs(-stick.getRawAxis(1)) <= deadband) {
      leftSpeed = 0;
    } else {
      leftSpeed = -stick.getRawAxis(1);

    }
    // Right deadband
    if(Math.abs(-stick.getRawAxis(5)) <= deadband) {
      rightSpeed = 0;
    } else {
      rightSpeed = -stick.getRawAxis(5);
    }
    drive.tankDrive(leftSpeed, rightSpeed);
}

public void GTADrive(Joystick stick) {
  double rightTrigger = -stick.getRawAxis(4);
  double leftTrigger = -stick.getRawAxis(3);
  double leftStickX = -stick.getRawAxis(0);

  double triggerValue1 = rightTrigger - leftTrigger;
  double turnValue1 = -leftStickX;
  double leftOutput = -triggerValue1 + turnValue1;
  double rightOutput = -triggerValue1 - turnValue1;
  drive.tankDrive(leftOutput, rightOutput );
}

public void regularTankDrive(double left, double right) {
  drive.tankDrive(left, right);
}

public void regularArcadeDrive(double fwd, double turn) {
  drive.arcadeDrive(fwd, turn);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}





