// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Shooter;

public class Index extends Command {
  /** Creates a new Index. */
  private Intake intake;
  private Shooter shooter;
  private Timer timer = new Timer();

  public Index(Intake intake, Shooter shooter) {
    this.intake = intake;
    this.shooter = shooter;
    addRequirements(intake, shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.set(Constants.Intake.DEFAULT_INDEX_SPEED);
    timer.restart();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !intake.isLoaded() && shooter.isLoaded() && timer.hasElapsed(Constants.Intake.INDEX_TIME_SECONDS);
  }
}
