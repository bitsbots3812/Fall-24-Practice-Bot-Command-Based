// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Controllers;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/** Add your docs here. */
public class XboxController extends GenericController {
    
    private final int btnA = 1;
    private final int btnB = 2;
    private final int btnX = 3;
    private final int btnY = 4;
    private final int btnLB = 5;
    private final int btnRB = 6;
    private final int btnBack = 7;   
    private final int btnStart = 8;       
    private final int btnL3 = 9;
    private final int btnR3 = 10;
    public final int leftXAxis = 0;
    public final int leftYAxis = 1;
    public final int leftTrigger = 2;
    public final int rightTrigger = 3;
    public final int rightXAxis = 4;
    public final int rightYAxis = 5;

    public JoystickButton buttonA     = new JoystickButton(joy, btnA);
    public JoystickButton buttonB     = new JoystickButton(joy, btnB);
    public JoystickButton buttonX     = new JoystickButton(joy, btnX);
    public JoystickButton buttonY     = new JoystickButton(joy, btnY);
    public JoystickButton buttonLB    = new JoystickButton(joy, btnLB);
    public JoystickButton buttonRB    = new JoystickButton(joy, btnRB);
    public JoystickButton buttonBack  = new JoystickButton(joy, btnBack);
    public JoystickButton buttonStart = new JoystickButton(joy, btnStart);
    public JoystickButton buttonR3    = new JoystickButton(joy, btnR3);
    public JoystickButton buttonL3    = new JoystickButton(joy, btnL3);

    public POVButton dPadUp    = new POVButton(joy, 0);
    public POVButton dPadRight = new POVButton(joy, 90);
    public POVButton dPadDown  = new POVButton(joy, 180);
    public POVButton dPadLeft  = new POVButton(joy, 270);

    void setLeftRumble(double value) {
        joy.setRumble(RumbleType.kLeftRumble, value);
    }

    void setRightRumble(double value) {
        joy.setRumble(RumbleType.kRightRumble, value);
    }

    public XboxController(int id) {
        super(id);
    }
}
