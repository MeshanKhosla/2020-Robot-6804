package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {

  // DO NOT DELETE
  private RobotContainer m_robotContainer;

  private Command m_autoCommand;

    
  @Override
  public void robotInit() {
    // DO NOT DELETE
    m_robotContainer = new RobotContainer();
    

  }

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autoCommand = m_robotContainer.getAutonomousCommand();

    // Checks if there is a valid auto command & starts if there is
    if(m_autoCommand != null) {
      m_autoCommand.schedule();
    }
  }


  @Override
  public void autonomousPeriodic() {

   
  }

  @Override
  public void teleopInit() {
    if(m_autoCommand != null) {
      m_autoCommand.cancel();
    }
    
  }

  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
