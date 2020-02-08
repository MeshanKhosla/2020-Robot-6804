/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {



  
  private NetworkTable table;
  private double tx,ty,ta,tv;
  private ArrayList <Double> m_targetList;
  private final int MAX_ENTRIES = 50;

   
  public Limelight() {
    // Limelight network table
    table = NetworkTableInstance.getDefault().getTable("limelight");
    System.out.println("Limelight initialized");
    m_targetList = new ArrayList<Double>(MAX_ENTRIES);
    

    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // X offset
    tx = table.getEntry("tx").getDouble(0);
    // Y offset
    ty = table.getEntry("ty").getDouble(0);
    // Object area
    ta = table.getEntry("ta").getDouble(0);
    // Whether there's a target detected
    tv = table.getEntry("tv").getDouble(0);


    if (m_targetList.size() >= MAX_ENTRIES)
    {
      m_targetList.remove(0);
    }
    m_targetList.add(ta);



  }




  // Getters and setters

  public double getXOffset() {
    return tx;
  }

  public double getYOffset() {
    return ty;
  }

  public double getArea() {
    double sum = 0;

    for(Double num : m_targetList)
    {
      sum += num.doubleValue();
    }
    return sum/m_targetList.size();
  }

  public NetworkTableEntry getPipeline() {
    return table.getEntry("pipeline");
  }


   // Checks if there is a valid limelight target detected
   public boolean hasTarget() {
    //return (double) (table.getEntry("tv").getNumber(0)) == 1;
    return(tv == 1.0);
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
