package frc.robot.Controllers;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.GenericHID;

public class AxisSupplier implements DoubleSupplier {
    GenericHID hidController;
    int axisId;
    boolean squared;
    double deadzone;
    boolean inverted;

    //Creates class for an axis that can be passed to commands. Note: Deadzones are applied before squaring.
    public AxisSupplier(GenericHID HIDController, int AxisID, boolean Squared, double Deadzone, boolean Inverted) {
        hidController = HIDController;
        axisId = AxisID;
        squared = Squared;
        deadzone = Deadzone;
        inverted = Inverted;
    }
    
    //TODO: Interpolate from zero
    public double getAsDouble () {
        if (squared) {
            double value = MathUtil.applyDeadband(hidController.getRawAxis(axisId), deadzone);
            return (inverted ? -1 : 1) * Math.copySign(value * value, value);
        }
        else {
            return (inverted ? -1 : 1) * MathUtil.applyDeadband(hidController.getRawAxis(axisId), deadzone);
        }
    }
}
