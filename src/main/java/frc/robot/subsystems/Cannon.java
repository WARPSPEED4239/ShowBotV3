package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Cannon extends SubsystemBase {
  private final Solenoid loadingSolenoid = new Solenoid(Constants.CANNON_LOADING_SOLENOID);
  private final Solenoid firingSolenoid = new Solenoid(Constants.CANNON_FIRING_SOLENOID);

  private final AnalogInput storagePressure = new AnalogInput(Constants.CANNON_STORAGE_PRESSURE);
  private final AnalogInput firingPressure = new AnalogInput(Constants.CANNON_FIRING_PRESSURE);
  private final Compressor compressor = new Compressor(Constants.COMPRESSOR);

  private final double DEFAULT_VOLTS = 4.52; // TODO Maybe have seperate tunes for each tank's desired PSI's
  private final double SLOPE = 250.0;
  private final double Y_INTERCEPT = -25.0;

  public Cannon() {}

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Loading Solenoid State", getLoadingSolenoidState());
    SmartDashboard.putBoolean("Firing Solenoid State", getFiringSolenoidState());

    SmartDashboard.putNumber("Storage Tank Pressure", getStorageTankPressure());
    SmartDashboard.putNumber("Storage Sensor Volts", getStorageSensorVolts());
    SmartDashboard.putNumber("Firing Tank Pressure", getFiringTankPressure());
    SmartDashboard.putNumber("Firing Sensor Volts", getFiringSensorVolts());

    SmartDashboard.putBoolean("Ready to Fire", getFiringTankPressure() >= 75.0);
    SmartDashboard.putBoolean("Compressor Status", getCompressorStatus());
  }

  public void setLoadingSolenoidState(boolean open) {
    loadingSolenoid.set(open);
  }

  public void setFiringSolenoidState(boolean open) {
    firingSolenoid.set(open);
  }

  public boolean getLoadingSolenoidState() {
    return loadingSolenoid.get();
  }

  public boolean getFiringSolenoidState() {
    return firingSolenoid.get();
  }

  public double getFiringTankPressure() {
    return SLOPE * firingPressure.getVoltage() / DEFAULT_VOLTS + Y_INTERCEPT;
  }

  public double getFiringSensorVolts() {
    return firingPressure.getVoltage();
  }

  public double getStorageTankPressure() {
    return SLOPE * storagePressure.getVoltage() / DEFAULT_VOLTS + Y_INTERCEPT;
  }

  public double getStorageSensorVolts() {
    return storagePressure.getVoltage();
  }

  public void turnOffCompressor() {
    compressor.stop();
  }

  public void turnOnCompressor() {
    compressor.start();
  }

  public boolean getCompressorStatus() {
    return compressor.enabled();
  }
}
