package frc.robot;

import com.ctre.phoenix.CANifier;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CannonAimSetPercentOutputWithController;
import frc.robot.commands.CannonFiringSolenoidSetState;
import frc.robot.commands.CannonLoadingSolenoidSetState;
import frc.robot.commands.CannonRevolveSetPercentOutput;
import frc.robot.commands.DrivetrainArcadeDrive;
import frc.robot.subsystems.Cannon;
import frc.robot.subsystems.CannonAngleAdjust;
import frc.robot.subsystems.CannonRevolve;
import frc.robot.subsystems.Drivetrain;
import frc.robot.tools.RGBController;

public class RobotContainer {
  private XboxController mXbox = new XboxController(0);
  
  private final Cannon mCannon = new Cannon();
  private final CannonRevolve mCannonRevolve = new CannonRevolve();
  private final CannonAngleAdjust mCannonAngleAdjust = new CannonAngleAdjust();
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final RGBController mRGBController = new RGBController(new CANifier(Constants.CANIFIER));
  private final double MIN_FIRING_PSI = 75.0;
  private final double MAX_FIRING_PSI = 80.0;

  public RobotContainer() {
    mCannon.setDefaultCommand(new CannonFiringSolenoidSetState(mCannon, false));
    mCannonRevolve.setDefaultCommand(new CannonRevolveSetPercentOutput(mCannonRevolve, 0.0));
    mCannonAngleAdjust.setDefaultCommand(new CannonAimSetPercentOutputWithController(mCannonAngleAdjust, mXbox));
    mDrivetrain.setDefaultCommand(new DrivetrainArcadeDrive(mDrivetrain, mXbox));

    configureButtonBindings();

    UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
		cam0.setResolution(320, 240);
    cam0.setFPS(10);
  }

  private void configureButtonBindings() {
    JoystickButton xButtonA, xButtonB, xButtonX, xButtonY, xButtonLeftBumper, xButtonRightBumper, xButtonLeftStick,
        xButtonRightStick;
        
    xButtonA = new JoystickButton(mXbox, 1);
		xButtonB = new JoystickButton(mXbox, 2);
		xButtonX = new JoystickButton(mXbox, 3);
		xButtonY = new JoystickButton(mXbox, 4);
		xButtonLeftBumper = new JoystickButton(mXbox, 5);
		xButtonRightBumper = new JoystickButton(mXbox, 6);
		xButtonLeftStick = new JoystickButton(mXbox, 9);
    xButtonRightStick = new JoystickButton(mXbox, 10);

    xButtonA.whileHeld(new CannonFiringSolenoidSetState(mCannon, true));    
    xButtonB.whenPressed(new CannonLoadingSolenoidSetState(mCannon, true));
    xButtonY.whenPressed(new CannonLoadingSolenoidSetState(mCannon, false));

    xButtonLeftBumper.whileHeld(new CannonRevolveSetPercentOutput(mCannonRevolve, -0.4));
    xButtonRightBumper.whileHeld(new CannonRevolveSetPercentOutput(mCannonRevolve, 0.4));

    // xButtonA.whenPressed(new ConditionalCommand(new ConditionalCommand(cannonFire(), new CannonRevolveSpin(mCannonRevolve, 1, -0.4), () -> mCannonRevolve.getRevolveLimitSwitch()), new InstantCommand(), () -> mCannon.getFiringTankPressure() >= MIN_FIRING_PSI));
    // xButtonB.whenPressed(new CannonRevolveSpin(mCannonRevolve, 8, 1.0));
    // xButtonX.whenPressed(new CannonRevolveSpin(mCannonRevolve, 8, -1.0));

    // xButtonLeftBumper.whenPressed(new CannonRevolveSpin(mCannonRevolve, 1, -0.4));
    // xButtonRightBumper.whenPressed(new CannonRevolveSpin(mCannonRevolve, 1, 0.4));
  }

  /* public Command cannonReloading() { // ERROR: If statement is only checked once on init
    if (mCannon.getFiringTankPressure() <= MIN_FIRING_PSI) {
      Command mCommand = new SequentialCommandGroup(
        new ParallelRaceGroup(
          new RGBSetColor(mRGBController, Color.Black),
          new CannonFiringSolenoidSetState(mCannon, false).withTimeout(0.5)
        ),
        new CannonLoadingSolenoidSetState(mCannon, true).withTimeout(0.5)
      );

      return mCommand;
    } else if (mCannon.getFiringTankPressure() >= MAX_FIRING_PSI) {
      return new CannonLoadingSolenoidSetState(mCannon, false).withTimeout(0.5);
    } else {
      return new RGBSetColor(mRGBController, Color.Red).withTimeout(0.1);
    }
  }

  public Command cannonFire() {
    Color[] redFlashing = {Color.Red, Color.Black};

    Command mCommand = new SequentialCommandGroup(
      new ParallelRaceGroup(
        new RGBSetColor(mRGBController, redFlashing, 0.2),
        new CannonLoadingSolenoidSetState(mCannon, false).withTimeout(1.0)
      ),
      new ParallelRaceGroup(
        new RGBSetColor(mRGBController, Color.White),
        new CannonFiringSolenoidSetState(mCannon, true).withTimeout(0.5)
      ),
      new RGBSetColor(mRGBController, Color.Black).withTimeout(0.5),
      new CannonRevolveSpin(mCannonRevolve, 1, 0.4)
    );

    return mCommand;
  } */

  public RGBController getRGBController() {
    return mRGBController;
  }
}
