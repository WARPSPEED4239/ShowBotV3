package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.tools.RGBController;
//import frc.robot.tools.RGBController.Color;

public class RGBSetColor extends CommandBase {
  /*private final RGBController mRGBController;
  private final Color mColor;
  private final Color[] mColors;
  private final double mCycleTime;

  public RGBSetColor(RGBController rgbController, Color color) {
    mRGBController = rgbController;
    mColor = color;
    mColors = null;
    mCycleTime = -1;
  }

  public RGBSetColor(RGBController rgbController, Color[] colors, double cycleTime){
    mRGBController = rgbController;
    mColor = null;
    mColors = colors;
    mCycleTime = cycleTime;
  }

  @Override
  public void initialize() {
    if (mColors == null) {
      mRGBController.setColor(mColor);
    } else {
      mRGBController.setColors(mColors, mCycleTime);
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }*/
}
