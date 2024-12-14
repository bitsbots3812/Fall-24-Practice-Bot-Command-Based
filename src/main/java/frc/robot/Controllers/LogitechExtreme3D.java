// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Controllers;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** Add your docs here. */
public class LogitechExtreme3D extends GenericController {

    public final int X_AXIS      = 0;
    public final int Y_AXIS      = 1;
    public final int RZ_AXIS     = 2;
    public final int SLIDER_AXIS = 3;

    private final int BTN_TRIGGER     = 1;
    private final int BTN_SIDE        = 2;
    private final int BTN_UPPER_LEFT  = 5;
    private final int BTN_UPPER_RIGHT = 6;
    private final int BTN_LOWER_LEFT  = 3;
    private final int BTN_LOWER_RIGHT = 4;
    private final int BTN_BASE_7      = 7;
    private final int BTN_BASE_8      = 8;
    private final int BTN_BASE_9      = 9;
    private final int BTN_BASE_10     = 10;
    private final int BTN_BASE_11     = 11;
    private final int BTN_BASE_12     = 12;

    public JoystickButton buttonTrigger    = new JoystickButton(joy, BTN_TRIGGER);
    public JoystickButton buttonSide       = new JoystickButton(joy, BTN_SIDE);
    public JoystickButton buttonUpperLeft  = new JoystickButton(joy, BTN_UPPER_LEFT);
    public JoystickButton buttonUpperRight = new JoystickButton(joy, BTN_UPPER_RIGHT);
    public JoystickButton buttonLowerLeft  = new JoystickButton(joy, BTN_LOWER_LEFT);
    public JoystickButton buttonLowerRight = new JoystickButton(joy, BTN_LOWER_RIGHT);
    public JoystickButton buttonBase7      = new JoystickButton(joy, BTN_BASE_7);
    public JoystickButton buttonBase8      = new JoystickButton(joy, BTN_BASE_8);
    public JoystickButton buttonBase9      = new JoystickButton(joy, BTN_BASE_9);
    public JoystickButton buttonBase10     = new JoystickButton(joy, BTN_BASE_10);
    public JoystickButton buttonBase11     = new JoystickButton(joy, BTN_BASE_11);
    public JoystickButton buttonBase12     = new JoystickButton(joy, BTN_BASE_12);

    public LogitechExtreme3D(int port) {
        super(port);
    }

    public double getRawX() {
        return joy.getRawAxis(X_AXIS);
    }

    public double getRawY() {
        return joy.getRawAxis(Y_AXIS);
    }

    public double getRawRZ() {
        return joy.getRawAxis(RZ_AXIS);
    }

    public double getRawSlider() {
        return joy.getRawAxis(SLIDER_AXIS);
    }

}
