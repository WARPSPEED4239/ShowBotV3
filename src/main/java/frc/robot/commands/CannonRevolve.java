package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cannon;

public class CannonRevolve extends CommandBase {
  private final Cannon mCannon;
  private final int mTargetNumberOfBarrels;
  private final double mPercentOutput;
  private int mNumberOfBarrelsAdvanced;
  private boolean waitingForLimit;

  public CannonRevolve(Cannon cannon, int targetNumberOfBarrels, double percentOutput) {
    mCannon = cannon;
    mTargetNumberOfBarrels = targetNumberOfBarrels;
    mPercentOutput = percentOutput;
    addRequirements(mCannon);
  }

  @Override
  public void initialize() {
    mNumberOfBarrelsAdvanced = 0;
    
    if (mCannon.getRevovleLimitSwitch()) {
      waitingForLimit = false;
    } else {
      waitingForLimit = true;
    }
  }

  @Override
  public void execute() {
    mCannon.setPercentOutput(mPercentOutput);

    if (waitingForLimit) {
      if (mCannon.getRevovleLimitSwitch()) {
        mNumberOfBarrelsAdvanced++;
        waitingForLimit = false;
      }
    } else {
      if (!mCannon.getRevovleLimitSwitch()) {
        waitingForLimit = true;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    mCannon.setPercentOutput(0.0);
  }

  @Override
  public boolean isFinished() {
    if (mNumberOfBarrelsAdvanced == mTargetNumberOfBarrels) {
      return true;
    } else {
      return false;
    }
  }
}
