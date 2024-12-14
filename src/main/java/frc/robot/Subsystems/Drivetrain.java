// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  private TalonSRX frontLeft  = new TalonSRX(Constants.Drivetrain.FRONT_LEFT_MOTOR_ID);
  private TalonSRX frontRight = new TalonSRX(Constants.Drivetrain.FRONT_RIGHT_MOTOR_ID);
  private TalonSRX rearLeft   = new TalonSRX(Constants.Drivetrain.REAR_LEFT_MOTOR_ID);
  private TalonSRX rearRight  = new TalonSRX(Constants.Drivetrain.REAR_RIGHT_MOTOR_ID);

  //Seriously CTRE? You can't just implement the standard interfaces like everyone else?
  private DifferentialDrive drivetrain = new DifferentialDrive((double d)->{frontLeft.set(TalonSRXControlMode.PercentOutput, d);}, 
                                                          (double d)->{frontRight.set(TalonSRXControlMode.PercentOutput, d);});

  public Drivetrain() {
    frontLeft.setInverted (Constants.Drivetrain.FL_INVERT);
    frontRight.setInverted(Constants.Drivetrain.FR_INVERT);
    rearLeft.setInverted  (Constants.Drivetrain.RL_INVERT);
    rearRight.setInverted (Constants.Drivetrain.RR_INVERT);

    rearLeft.follow(frontLeft);
    rearRight.follow(frontRight);
  }

  public void drive(double xSpeed, double zRotation) {
    drivetrain.arcadeDrive(xSpeed, zRotation);
  }

  @Override
  public void periodic() {}
}
