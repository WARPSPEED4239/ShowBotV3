package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Cannon;
import frc.robot.subsystems.CannonRevolve;

public class CannonFireRevovle extends CommandBase {

  private final Cannon mCannon;
  private final CannonRevolve mCannonRevolve;
  private boolean mNeededAlignment;

  private State mState;
  private enum State {
    UNITIALIZED,
    ALIGNMENT,
    FIRE,
    ROTATE_NEXT,
    ERROR,
    SUCCESS
  }

  private RotateState mRotateState;
  private enum RotateState {
    START_POSITION,
    MIDDLE_POSITION,
    END_POSITION
  }

  public CannonFireRevovle(Cannon cannon, CannonRevolve cannonRevolve) {
    mCannon = cannon;
    mCannonRevolve = cannonRevolve;
    updateState(State.UNITIALIZED);
    addRequirements(mCannon, mCannonRevolve);
  }

  @Override
  public void initialize() {
    mRotateState = null;

    if (checkPressure()) {
      updateState(State.ALIGNMENT);
    } else {
      updateState(State.ERROR);
    }
  }

  @Override
  public void execute() {
    switch (mState) {
      case ALIGNMENT:
        alignment();
        break;
      case FIRE:
        fire();
        break;
      case ROTATE_NEXT:
        rotateNext();
        break;
      default:
        break;
    }

    SmartDashboard.putString("Fire Revolve State", mState.name());
  }

  @Override
  public void end(boolean interrupted) {
    mCannonRevolve.setPercentOutput(0.0);
    mCannon.setFiringSolenoidState(false);
  }

  @Override
  public boolean isFinished() {
    return mState == State.SUCCESS || mState == State.ERROR;
  }

  private boolean checkPressure() {
      return mCannon.getFiringTankPressure() >= Constants.MIN_FIRING_PRESSURE;
  }

  private void updateState(State state) {
    if (state != mState) {
      mState = state;
    }
  }

  private void alignment() {
    if (mCannonRevolve.getRevolveLimitSwitch()) {
      if (mNeededAlignment) {
        updateState(State.ERROR);
      } else {
        updateState(State.FIRE);
      }
      return;
    }

    mNeededAlignment = true;
    mCannonRevolve.setPercentOutput(-0.75);
  }

  private void fire() {
    double now = Timer.getFPGATimestamp();

    if (mCannon.getLoadingSolenoidState()) {
      mCannon.setLoadingSolenoidState(false);
    }

    if (now - mCannon.getLoadingLastClosedTime() < 0.5) {
      return;
    }

    if (!mCannon.getFiringSolenoidState()) {
      mCannon.setFiringSolenoidState(true);
    }

    if (now - mCannon.getFiringLastOpenedTime() < 0.5) {
      return;
    }

    mCannon.setFiringSolenoidState(false);
    updateState(State.ROTATE_NEXT);
  }

  private void rotateNext() {
    if (mRotateState == null) {
      mRotateState = RotateState.START_POSITION;
    }
    
    switch (mRotateState) {
      case START_POSITION:
        if (!mCannonRevolve.getRevolveLimitSwitch()) {
          mRotateState = RotateState.MIDDLE_POSITION;
        }
        mCannonRevolve.setPercentOutput(0.75);
        break;
      case MIDDLE_POSITION:
        if (mCannonRevolve.getRevolveLimitSwitch()) {
          mRotateState = RotateState.END_POSITION;
        }
        mCannonRevolve.setPercentOutput(0.75);
        break;
      case END_POSITION:
        mCannonRevolve.setPercentOutput(0.0);
        updateState(State.SUCCESS);
        break;
    }
  }
}
