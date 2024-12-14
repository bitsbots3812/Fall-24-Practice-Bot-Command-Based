// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Controllers.LogitechExtreme3D;
import frc.robot.Controllers.XboxController;
import frc.robot.Subsystems.*;
import frc.robot.Commands.Shooter.*;
import frc.robot.Commands.Drivetrain.*;
import frc.robot.Commands.Intake.*;

public class RobotContainer {

  //Instantiate Subsystems
  Drivetrain drivetrain = new Drivetrain();
  Intake intake = new Intake();
  Shooter shooter = new Shooter();

  //Create Controllers
  LogitechExtreme3D driverStick = new LogitechExtreme3D(0);
  XboxController manipulatorController = new XboxController(1);

  public RobotContainer() {
    configureBindings();

  }

  private void configureBindings() {

    drivetrain.setDefaultCommand(new DefaultDrive(drivetrain, 
      driverStick.getAxisSupplier(
        driverStick.Y_AXIS, 
        true, 
        0,
        true
      ),
      driverStick.getAxisSupplier(
        driverStick.X_AXIS, 
        true, 
        0,
        true
      )
    ));

    driverStick.buttonTrigger.onTrue(new Fire(shooter));
    driverStick.buttonSide.whileTrue(new IntakeSequence(intake, shooter).handleInterrupt(() -> {intake.setExtended(false);}));

    shooter.setDefaultCommand(new DefaultShooterCommand(
      shooter,
      manipulatorController.getAxisTrigger(
          manipulatorController.leftYAxis,
          -0.9,
          false
      ), 
      manipulatorController.getAxisTrigger(
          manipulatorController.leftYAxis,
          0.9,
          true
      )
    ));

    manipulatorController.buttonY.onTrue(new DeployIntake(intake));
    manipulatorController.buttonA.onTrue(new RetractIntake(intake));

    manipulatorController.buttonX.whileTrue(new RunIntake(intake, Constants.Intake.DEFAULT_INTAKE_SPEED));
    manipulatorController.buttonB.whileTrue(new RunIntake(intake, -Constants.Intake.DEFAULT_INTAKE_SPEED));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
