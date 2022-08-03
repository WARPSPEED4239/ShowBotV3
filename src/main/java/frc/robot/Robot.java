package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import frc.robot.tools.RGBController.Color;

public class Robot extends TimedRobot {
  private RobotContainer mRobotContainer;

  @Override
  public void robotInit() {
    mRobotContainer = new RobotContainer();

    //Color[] colors = {Color.RedDim, Color.Black};
    //mRobotContainer.getRGBController().setColors(colors, 1.5);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    //Color[] colors = {Color.RedDim, Color.Black};
    //mRobotContainer.getRGBController().setColors(colors, 1.5);
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
