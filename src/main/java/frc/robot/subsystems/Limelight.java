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
  
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta"); 

  
  public Limelight() {
 
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

  public void setPipeline(int pipeline) {
    table.getEntry("pipeline").setNumber(pipeline);
  }
}
