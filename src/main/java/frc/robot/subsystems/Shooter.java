/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;





public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   *
   */ 
  
  private WPI_TalonSRX leftFlyWheel;
  private WPI_TalonSRX rightFlyWheel;

  //  Speedcontrollers
 

    public Shooter() {
      // Right flywheel
      //rightFlyWheel = new WPI_VictorSPX(7);
      leftFlyWheel = new WPI_TalonSRX(3); 
      rightFlyWheel = new WPI_TalonSRX(0);


    // Left flywheel (Inverted)
    
    rightFlyWheel.setInverted(false);
    leftFlyWheel.setInverted(true);
      
  
     
    }
  
    // Getters
    // Returns right flywheel
    public WPI_TalonSRX getRightFlywheel() {
      return rightFlyWheel;
    }
  
  
  
    // Returns left flywheel
    public WPI_TalonSRX getLeftFlywheel(){
      return leftFlyWheel;
    }
     
  
    public void setShooterSpeed(double speed) {
      rightFlyWheel.set(speed);
      leftFlyWheel.set(speed);
    }
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

	public void publishToSmartDashboard() {
	}
    
  }

