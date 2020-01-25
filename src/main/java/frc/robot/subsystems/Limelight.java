/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  
  private NetworkTable table;
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  NetworkTableEntry tv;

  
  public Limelight() {
    // Limelight network table
    table = NetworkTableInstance.getDefault().getTable("limelight");
    System.out.println("Limelight initialized");

    // X offset
    tx = table.getEntry("tx");
    // Y offset
    ty = table.getEntry("ty");
    // Object area
    ta = table.getEntry("ta");
    // Whether there's a target detected
    tv = table.getEntry("tv");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Getters and setters

  public NetworkTableEntry getXOffset() {
    return tx;
  }

  public NetworkTableEntry getYOffset() {
    return ty;
  }

  public NetworkTableEntry getArea() {
    return ta;
  }

  public int getPipeline() {
    return table.getEntry("pipeline");
  }


   // Checks if there is a valid limelight target detected
   public boolean hasTarget() {
    if(tv == 0) {
      return true;
    } else if(tv == 1) {
      return false;
    }
  }

  // Up to 10 pipelines
  public void setPipeline(int pipeline) {
    if(pipeline >= 0 && pipeline <= 9) {
      table.getEntry("pipeline").setNumber(pipeline);
    }
  }

  // 0 = Vision processor
  // 1 = Driver camera 
  public void setCamMode(int mode) {
    if (mode >= 0 && mode <= 1) {
      table.getEntry("camMode").setNumber(mode);
    }
  }

  // 0 = Current pipeline LED mode
  // 1 = Force off
  // 2 = Force blink
  // 3 = Force on
  public void setLedMode(int mode) {
    if (mode >= 0 && mode <= 3) {
      table.getEntry("ledMode").setNumber(mode);
    }
  }

}
