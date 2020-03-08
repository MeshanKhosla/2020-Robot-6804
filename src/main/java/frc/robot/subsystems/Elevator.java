/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;





public class Elevator extends SubsystemBase {

  private WPI_VictorSPX elevatorUpMotor;
  private WPI_VictorSPX elevatorDownMotor;
  // Speedcontrollers

  public Elevator() {
    // flywheels
    elevatorUpMotor = new WPI_VictorSPX(10);
    elevatorDownMotor = new WPI_VictorSPX(9);

  }

  // Getters
  // Returns motor that moves elevator up
  public WPI_VictorSPX getElevatorUpMotor() {
    return elevatorUpMotor;
  }
  // Returns motor that moves elevator down
  public WPI_VictorSPX getElevatorDownMotor() {
    return elevatorDownMotor;
  }
  public void setElevatorSpeed(double upSpeed, double downSpeed) {
    elevatorUpMotor.set(upSpeed);
    elevatorDownMotor.set(downSpeed);
  }
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

    
  }

