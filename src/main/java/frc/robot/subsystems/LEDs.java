/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase {
  /**
   * Creates a new LEDs.
   */
  private Spark ledController;
  public LEDs() {
    ledController = new Spark(0);
  }

  public void setColorPattern(double pattern){
    ledController.set(pattern);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
