// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Drivetrain;

public class DefaultDrive extends Command {
  /** Creates a new DefaultDrive. */

  private Drivetrain drivetrain;
  private DoubleSupplier xSpeed;
  private DoubleSupplier zRotation;

  public DefaultDrive(Drivetrain drivetrain, DoubleSupplier xSpeed, DoubleSupplier zRotation) {
    this.drivetrain = drivetrain;
    this.xSpeed = xSpeed;
    this.zRotation = zRotation;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.drive(xSpeed.getAsDouble(), zRotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
