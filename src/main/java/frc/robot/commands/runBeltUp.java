/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ArrayList;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Belt;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class runBeltUp extends CommandBase {



  private final Belt m_beltSubsystem;

  public runBeltUp(Belt beltSubsystem) {
    m_beltSubsystem = beltSubsystem;
    addRequirements(m_beltSubsystem);
  }

  // Called when the command is initially scheduled.
  private Pixy2 pixy;
  private boolean isCamera = false;
  private int state = -1;
  @Override
  public void initialize() {
    pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
    pixy.init(0);
    System.out.println(pixy.init(0));

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 1);
     ArrayList<Block> blocks = pixy.getCCC().getBlocks();


     if(blocks.size() > 0) {
       double xcoord = blocks.get(0).getX();
       double ycoord = blocks.get(0).getY();
       String data = blocks.get(0).toString();

       SmartDashboard.putBoolean("Belt Pixy detect ball", true);
        SmartDashboard.putNumber("X coord", xcoord);
        SmartDashboard.putNumber("Y coord", ycoord);
       SmartDashboard.putString("Data", data);
       SmartDashboard.putNumber("Belt Pixy Size", blocks.size());


    //   // Run speed
     m_beltSubsystem.runBelt(.25);

     } else {
       SmartDashboard.putBoolean("Belt Pixy detect ball", false);
       SmartDashboard.putNumber("Belt Pixy Size", blocks.size());
       m_beltSubsystem.runBelt(0);
     }
    


  //  m_beltSubsystem.runBelt(.25);


    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
