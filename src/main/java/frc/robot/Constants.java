package frc.robot;

public class Constants {
    private Constants() {} //No instantiation

    public static class Drivetrain {
        public static final int FRONT_LEFT_MOTOR_ID  = 1;
        public static final int FRONT_RIGHT_MOTOR_ID = 3;
        public static final int REAR_LEFT_MOTOR_ID   = 0;
        public static final int REAR_RIGHT_MOTOR_ID  = 2;

        public static final boolean FL_INVERT = true;
        public static final boolean FR_INVERT = true;
        public static final boolean RL_INVERT = false;
        public static final boolean RR_INVERT = false;
    }

    public static class Intake {
        public static final int INTAKE_MOTOR_UPPER_ID = 5;
        public static final int INTAKE_MOTOR_LOWER_ID = 6;

        public static final boolean UPPER_INVERT = true;
        public static final boolean LOWER_INVERT = false;

        public static final int SENSOR_PIN = 0;

        public static final double DEFAULT_INTAKE_SPEED = 0.5;
        public static final double DEFAULT_INDEX_SPEED  = 1.0;

        public static final double DEPLOY_TIME_SECONDS  = 1.0;
        public static final double RETRACT_TIME_SECONDS = 3.0;
        public static final double INDEX_TIME_SECONDS   = 1.0;

        public static final int LEFT_EXTEND_SOLENOID_ID   = 6;
        public static final int RIGHT_EXTEND_SOLENOID_ID  = 0;
        public static final int LEFT_RETRACT_SOLENOID_ID  = 7;
        public static final int RIGHT_RETRACT_SOLENOID_ID = 1;
    }

    public static class Shooter {
        public static final int SHOOTER_MOTOR_LEFT_ID  = 7;
        public static final int SHOOTER_MOTOR_RIGHT_ID = 8;

        public static final boolean LEFT_INVERT  = false;
        public static final boolean RIGHT_INVERT = true;

        public static final double SHOOT_TIME_SECONDS = .3;
        public static final double RESET_TIME_SECONDS = 1.0;

        public static final int SHOOTER_ELEVATION_PIN = 0;
        public static final int ELEVATION_LIMIT_SWITCH_PIN = 2;

        public static final int SENSOR_PIN = 1;
    }

}
