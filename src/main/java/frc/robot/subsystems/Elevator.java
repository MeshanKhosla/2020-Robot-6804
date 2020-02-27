/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;





public class Elevator extends SubsystemBase {

  private static WPI_TalonSRX ElevatorMotor;
  private int MotorOrigin;
  // Speedcontrollers

  public Elevator() {
    // flywheels
    ElevatorMotor = new WPI_TalonSRX(MotorOrigin);
    ElevatorMotor.setInverted(true);

  }

  // Getters
  // Returns Elevator
  public WPI_TalonSRX GetElevatorMotor() {
    return ElevatorMotor;
  }

  public static void SetElevatorSpeed(Joystick Control) {
    ElevatorMotor.set(Control.getRawAxis(3));
    }
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

    
  }

