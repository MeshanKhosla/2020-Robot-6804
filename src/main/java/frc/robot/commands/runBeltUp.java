/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ArrayList;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Belt;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class runBeltUp extends CommandBase {

  private final Belt m_beltSubsystem;
  private Pixy2 pixy;

  public runBeltUp(Belt beltSubsystem) {
    m_beltSubsystem = beltSubsystem;
    addRequirements(m_beltSubsystem);
  }

  @Override
  public void initialize() {
    pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
    pixy.init(0);
    //System.out.println(pixy.init(0));
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    ArrayList<Block> blocks = pixy.getCCC().getBlocks();
    pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 1);
    if(blocks.size() > 0) {
    SmartDashboard.putBoolean("Belt Pixy detect ball", true);
    // Run belt if pixy sees ball
      m_beltSubsystem.runBelt(-0.25);
    } else {
      SmartDashboard.putBoolean("Belt Pixy detect ball", false);
      m_beltSubsystem.runBelt(0);
    }    
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
