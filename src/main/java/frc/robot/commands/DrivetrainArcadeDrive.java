package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainArcadeDrive extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final XboxController mXbox;

  public DrivetrainArcadeDrive(Drivetrain drivetrain, XboxController xbox) {
    mDrivetrain = drivetrain;
    mXbox = xbox;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double move = mXbox.getRightTriggerAxis() - mXbox.getLeftTriggerAxis();
    double rotate = (.533333 * Math.pow(mXbox.getLeftX(), 3) + .466666 *  mXbox.getLeftX());

    if (rotate > 0.85){
      rotate = 0.85;
    }
    else if (rotate < -0.85) {
      rotate = -0.85;
    }
    
    mDrivetrain.arcadeDrive(move, rotate);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
