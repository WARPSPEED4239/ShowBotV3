package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private final int CURRENT_LIMIT = 45;
	private final double RAMP_RATE = 0.05;
	
	private CANSparkMax leftMaster = new CANSparkMax(Constants.DRIVETRAIN_MOTOR_LEFT_1, MotorType.kBrushless);
	private CANSparkMax leftSlave = new CANSparkMax(Constants.DRIVETRAIN_MOTOR_LEFT_2, MotorType.kBrushless);
	private CANSparkMax rightMaster = new CANSparkMax(Constants.DRIVETRAIN_MOTOR_RIGHT_1, MotorType.kBrushless);
	private CANSparkMax rightSlave = new CANSparkMax(Constants.DRIVETRAIN_MOTOR_RIGHT_2, MotorType.kBrushless);
  
  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);
	
	public Drivetrain() {
    leftMaster.restoreFactoryDefaults();
    leftSlave.restoreFactoryDefaults();
    rightMaster.restoreFactoryDefaults();
    rightSlave.restoreFactoryDefaults();

		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);
    
    leftMaster.setIdleMode(IdleMode.kBrake);
    leftSlave.setIdleMode(IdleMode.kBrake);
    rightMaster.setIdleMode(IdleMode.kBrake);
    rightSlave.setIdleMode(IdleMode.kBrake);

    leftMaster.setSmartCurrentLimit(CURRENT_LIMIT);
    leftSlave.setSmartCurrentLimit(CURRENT_LIMIT);
    rightMaster.setSmartCurrentLimit(CURRENT_LIMIT);
    rightSlave.setSmartCurrentLimit(CURRENT_LIMIT);

    leftMaster.setOpenLoopRampRate(RAMP_RATE);
    leftSlave.setOpenLoopRampRate(RAMP_RATE);
    rightMaster.setOpenLoopRampRate(RAMP_RATE);
    rightSlave.setOpenLoopRampRate(RAMP_RATE);

    leftMaster.burnFlash();
    leftSlave.burnFlash();
    rightMaster.burnFlash();
    rightSlave.burnFlash();
	}
	
	public void arcadeDrive(double move, double rotate) {
		final double MIN_MOVE_THRESHOLD = 0.05;
		final double MIN_ROTATE_THRESHOLD = 0.20;
		if (Math.abs(move) < MIN_MOVE_THRESHOLD)
			move = 0.0;
		if (Math.abs(rotate) < MIN_ROTATE_THRESHOLD)
			rotate = 0.0;
		
    	drive.arcadeDrive(move, rotate);
	}
}
