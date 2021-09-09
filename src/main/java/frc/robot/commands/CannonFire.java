package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cannon;
import frc.robot.subsystems.CannonRevolve;
import frc.robot.tools.RGBController;
import frc.robot.tools.RGBController.Color;

public class CannonFire extends CommandBase {
  private final Cannon mCannon;
  private final CannonRevolve mCannonRevolve;
  private final RGBController mRGBController;
  private final Color[] redFlashing = {Color.Red, Color.Black};

  public CannonFire(Cannon cannon, CannonRevolve cannonRevolve, RGBController RGBController) {
    mCannon = cannon;
    mCannonRevolve = cannonRevolve;
    mRGBController = RGBController;
    addRequirements(mCannon, mCannonRevolve);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mRGBController.setColors(redFlashing, 0.2);
    mCannon.setLoadingSolenoidState(false);
    //1.0 Delay
    mRGBController.setColor(Color.White);
    mCannon.setFiringSolenoidState(true);
    //0.5 Delay
    mRGBController.setColor(Color.Black);
    //Revolve
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
