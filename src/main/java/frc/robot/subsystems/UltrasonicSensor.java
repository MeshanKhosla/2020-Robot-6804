/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;






public class UltrasonicSensor extends SubsystemBase {
  private static final double VOLTS_TO_DIST = 1.0;
  /**
   * Creates a new UltrasonicSensor.
   * 
   */
  //* Goal: Convert the voltage to distance  */

  AnalogInput mb1013 = new AnalogInput(0); 

  
  
  public double getVoltage()
  {
    return mb1013.getVoltage();
  }


  public double getDistance()
  {
    return getVoltage() * VOLTS_TO_DIST;
  }

  public void updateDashboard() 
  {
    SmartDashboard.putNumber("Distance (Volts)", getVoltage());
    SmartDashboard.putNumber("Distance (real)", getDistance());
  }

  

  public UltrasonicSensor() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
