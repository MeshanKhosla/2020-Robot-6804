/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */

  private WPI_VictorSPX intakeMotor;
  public Intake() {
    intakeMotor = new WPI_VictorSPX(7);
  }

  public WPI_VictorSPX getIntakeMotor() {
    return intakeMotor;
  }

  public void runIntake(double speed) {
    intakeMotor.set(speed);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
