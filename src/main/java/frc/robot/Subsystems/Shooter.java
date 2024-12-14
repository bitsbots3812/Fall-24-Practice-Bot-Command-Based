// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */

  private VictorSPX left  = new VictorSPX(Constants.Shooter.SHOOTER_MOTOR_LEFT_ID);
  private VictorSPX right = new VictorSPX(Constants.Shooter.SHOOTER_MOTOR_RIGHT_ID);

  private Relay elevationMotor = new Relay(Constants.Shooter.SHOOTER_ELEVATION_PIN);

  private Timer shotTimer = new Timer();

  private DigitalInput sensor = new DigitalInput(Constants.Shooter.SENSOR_PIN);
  private DigitalInput limitSwitch = new DigitalInput(Constants.Shooter.ELEVATION_LIMIT_SWITCH_PIN);

  private Relay.Value elevationDirection = Value.kOff;

  enum ShooterState {
    FIRING_STAGE_1(true),
    FIRING_STAGE_2(true),
    IDLE(false);

    boolean firing;

    ShooterState(boolean firing) {
      this.firing = firing;
    }
  }

  private ShooterState state = ShooterState.IDLE;

  public Shooter() {
    left.setInverted(Constants.Shooter.LEFT_INVERT);
    right.setInverted(Constants.Shooter.RIGHT_INVERT);
  }

  public void shoot() {
    if (state == ShooterState.IDLE) {
      state = ShooterState.FIRING_STAGE_1;
      shotTimer.reset();
      shotTimer.start();
    }
  }

  public void setElevationMotor(Relay.Value direction) {
    elevationDirection = direction;
  }

  public boolean isFiring() {
    return state.firing;
  }

  public boolean isLoaded() {
    return !sensor.get();
  }

  public boolean isElevated() {
    return !limitSwitch.get();
  }

  @Override
  public void periodic() {
    if (state == ShooterState.FIRING_STAGE_1 && !shotTimer.hasElapsed(Constants.Shooter.SHOOT_TIME_SECONDS)) {
      left. set(VictorSPXControlMode.PercentOutput, 1);
      right.set(VictorSPXControlMode.PercentOutput, 1);
    }
    else if (state == ShooterState.FIRING_STAGE_1) {
      state = ShooterState.FIRING_STAGE_2;
    }
    else if (state == ShooterState.FIRING_STAGE_2) {
      left. set(VictorSPXControlMode.PercentOutput, 0);
      right.set(VictorSPXControlMode.PercentOutput, 0);

      if (shotTimer.hasElapsed(Constants.Shooter.SHOOT_TIME_SECONDS + Constants.Shooter.RESET_TIME_SECONDS)) {
        state = ShooterState.IDLE;
      }
    }

    if (elevationDirection == Value.kReverse && !limitSwitch.get()) {
      elevationMotor.set(Value.kOff);
    }
    else {
      elevationMotor.set(elevationDirection);
    }
  }
}
