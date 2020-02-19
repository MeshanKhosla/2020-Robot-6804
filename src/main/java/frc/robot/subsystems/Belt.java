/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Belt extends SubsystemBase {
  /**
   * Creates a new Belt.
   */

  private WPI_VictorSPX beltMotor;
  public Belt() {
    beltMotor = new WPI_VictorSPX(6);
  }


  public void runBelt(double speed) {
    beltMotor.set(speed);
  }

  public WPI_VictorSPX getBeltMotor() {
    return beltMotor;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
