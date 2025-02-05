// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Shooter;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter;

public class DefaultShooterCommand extends Command {
  /** Creates a new DefaultShooterCommand. */

  private Shooter shooter;
  private BooleanSupplier up;
  private BooleanSupplier down;

  public DefaultShooterCommand(Shooter shooter, BooleanSupplier up, BooleanSupplier down) {
    this.shooter = shooter;
    this.up = up;
    this.down = down;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (up.getAsBoolean() && !down.getAsBoolean()) {
      shooter.setElevationMotor(Value.kForward);
    }
    else if (!up.getAsBoolean() && down.getAsBoolean()) {
      shooter.setElevationMotor(Value.kReverse);
    }
    else {
      shooter.setElevationMotor(Value.kOff);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.setElevationMotor(Value.kOff);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
