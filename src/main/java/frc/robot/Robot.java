// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.hal.CTREPCMJNI;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Controllers.LogitechExtreme3D;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private boolean testMode = false;


  private final  PowerDistribution pdp = new PowerDistribution(0, ModuleType.kCTRE);

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    //pneumatics.enableCompressorDigital();
    //pneumatics.clearStickyFaults();
    pdp.clearStickyFaults();

    CameraServer.startAutomaticCapture();
    CameraServer.startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() {
    if (!testMode) {
      CommandScheduler.getInstance().run();
    }
  }

  @Override
  public void disabledInit() {
    testMode = false;
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  LogitechExtreme3D joy = new LogitechExtreme3D(0);
  SendableChooser<BaseMotorController> motors = new SendableChooser<BaseMotorController>();
  DigitalInput intakeSensor;
  DigitalInput shooterSensor;

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    testMode = true;

    motors.setDefaultOption("DrivetrainFL", new TalonSRX(Constants.Drivetrain.FRONT_LEFT_MOTOR_ID));
    motors.addOption("DrivetrainFR", new TalonSRX(Constants.Drivetrain.FRONT_RIGHT_MOTOR_ID));
    motors.addOption("DrivetrainRL", new TalonSRX(Constants.Drivetrain.REAR_LEFT_MOTOR_ID));
    motors.addOption("DrivetrainRR", new TalonSRX(Constants.Drivetrain.REAR_RIGHT_MOTOR_ID));

    motors.addOption("ShooterL", new VictorSPX(Constants.Shooter.SHOOTER_MOTOR_LEFT_ID));
    motors.addOption("ShooterR", new VictorSPX(Constants.Shooter.SHOOTER_MOTOR_RIGHT_ID));

    motors.addOption("IntakeUpper", new VictorSPX(Constants.Intake.INTAKE_MOTOR_UPPER_ID));
    motors.addOption("IntakeLower", new VictorSPX(Constants.Intake.INTAKE_MOTOR_LOWER_ID));

  }

  @Override
  public void testPeriodic() {
    if (motors.getSelected() != null) {
      motors.getSelected().set(ControlMode.PercentOutput, -joy.getRawY());
    }

    SmartDashboard.putBoolean("Intake Sensor",  m_robotContainer.intake.isLoaded());
    SmartDashboard.putBoolean("Shooter Sensor", m_robotContainer.shooter.isLoaded());
    SmartDashboard.putBoolean("Intake Limit Switch", m_robotContainer.shooter.isElevated());
  }

  @Override
  public void testExit() {}
}
