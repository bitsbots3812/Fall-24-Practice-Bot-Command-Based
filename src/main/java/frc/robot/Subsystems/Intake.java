// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  private VictorSPX intakeUpper = new VictorSPX(Constants.Intake.INTAKE_MOTOR_UPPER_ID);
  private VictorSPX intakeLower = new VictorSPX(Constants.Intake.INTAKE_MOTOR_LOWER_ID);

  private DigitalInput sensor = new DigitalInput(Constants.Intake.SENSOR_PIN);

  //private boolean isDeployed = false;

  private DoubleSolenoid leftPiston = new DoubleSolenoid(
    PneumaticsModuleType.CTREPCM, 
    Constants.Intake.LEFT_EXTEND_SOLENOID_ID, 
    Constants.Intake.LEFT_RETRACT_SOLENOID_ID
  );

  private DoubleSolenoid rightPiston = new DoubleSolenoid(
    PneumaticsModuleType.CTREPCM, 
    Constants.Intake.RIGHT_EXTEND_SOLENOID_ID, 
    Constants.Intake.RIGHT_RETRACT_SOLENOID_ID
  );

  public Intake() {
    intakeUpper.setInverted(Constants.Intake.UPPER_INVERT);
    intakeLower.setInverted(Constants.Intake.LOWER_INVERT);
    intakeUpper.setNeutralMode(NeutralMode.Brake);
    intakeLower.setNeutralMode(NeutralMode.Brake);
  }

  public void set(double upperSpeed, double lowerSpeed) {
    intakeUpper.set(VictorSPXControlMode.PercentOutput, upperSpeed);
    intakeLower.set(VictorSPXControlMode.PercentOutput, lowerSpeed);
  }

  public void set(double speed) {
    set(speed, speed);
  }

  public void setExtended(boolean extended) {
    leftPiston.set (extended ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    rightPiston.set(extended ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
  }

  /*public boolean getExtended() {
    return isDeployed;
  }*/

  public boolean isLoaded() {
    return !sensor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
