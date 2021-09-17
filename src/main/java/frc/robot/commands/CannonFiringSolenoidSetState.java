package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cannon;

public class CannonFiringSolenoidSetState extends CommandBase {
  private final Cannon mCannon;
  private final boolean mOpen;

  public CannonFiringSolenoidSetState(Cannon cannon, boolean open) {
    mCannon = cannon;
    mOpen = open;
    addRequirements(mCannon);
  }

  @Override
  public void initialize() {
    mCannon.setFiringSolenoidState(mOpen);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
