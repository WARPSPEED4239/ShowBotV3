package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Cannon;
import frc.robot.tools.RGBController;
import frc.robot.tools.RGBController.Color;

public class CannonReloading extends CommandBase {
  private final Cannon mCannon;
  private final RGBController mRGBController;

  public CannonReloading(Cannon cannon, RGBController RGBController) {
    mCannon = cannon;
    mRGBController = RGBController;
    addRequirements(mCannon);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (mCannon.getFiringTankPressure() <= Constants.MIN_FIRING_PRESSURE) {
      mRGBController.setColor(Color.Black);
      mCannon.setLoadingSolenoidState(true);
    } else if (mCannon.getFiringTankPressure() >= Constants.MAX_FIRING_PRESSURE) {
      mCannon.setLoadingSolenoidState(false);
    } else {
      mRGBController.setColor(Color.Red);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
