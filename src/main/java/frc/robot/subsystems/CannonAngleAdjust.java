package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CannonAngleAdjust extends SubsystemBase {
  private final int CURRENT_LIMIT = 45;
  private final double RAMP_RATE = 0.05;

  private final CANSparkMax angleAdjustMotor = new CANSparkMax(Constants.CANNON_ANGLE_ADJUST_MOTOR, MotorType.kBrushed);
  private final DigitalInput topLimitSwtich = new DigitalInput(Constants.CANNON_ANGLE_ADJUST_TOP_LIMIT_SWITCH);
  private final DigitalInput bottomLimitSwitch = new DigitalInput(Constants.CANNON_ANGLE_ADJUST_BOTTOM_LIMIT_SWITCH);

  public CannonAngleAdjust() {
    angleAdjustMotor.restoreFactoryDefaults();
    angleAdjustMotor.setInverted(false);
    angleAdjustMotor.setIdleMode(IdleMode.kBrake);
    angleAdjustMotor.setSmartCurrentLimit(CURRENT_LIMIT);
    angleAdjustMotor.setOpenLoopRampRate(RAMP_RATE);
    angleAdjustMotor.burnFlash();
  }
  
  @Override
  public void periodic() {
  }

  public void setPercentOutput(double output) {
    if (output > 1.0) {
      output = 1.0;
    } else if (output < -1.0) {
      output = -1.0;
    }

    angleAdjustMotor.set(output);
  }

  public double getMotorOutputCurrent() {
    return angleAdjustMotor.getOutputCurrent();
  }

  public boolean getTopLimitSwtich() {
    return !topLimitSwtich.get();
  }

  public boolean getBottomLimitSwtich() {
    return !bottomLimitSwitch.get();
  }
}
