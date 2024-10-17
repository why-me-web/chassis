// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  CANSparkMax flMotor = new CANSparkMax(DriveConstants.flSparkID, MotorType.kBrushless); 
  CANSparkMax blMotor = new CANSparkMax(DriveConstants.blSparkID, MotorType.kBrushless); 
  CANSparkMax frMotor = new CANSparkMax(DriveConstants.frSparkID, MotorType.kBrushless); 
  CANSparkMax brMotor = new CANSparkMax(DriveConstants.brSparkID, MotorType.kBrushless); 
  DifferentialDrive drive = new DifferentialDrive(flMotor,frMotor);

  public DriveSubsystem() {
    flMotor.restoreFactoryDefaults();
    frMotor.restoreFactoryDefaults();
    blMotor.restoreFactoryDefaults();
    brMotor.restoreFactoryDefaults();
    blMotor.follow(flMotor);
    brMotor.follow(frMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public Command drive(DoubleSupplier speed, DoubleSupplier rotation) {
    return runEnd(()->{
      drive.arcadeDrive(speed.getAsDouble(),rotation.getAsDouble());
    }, ()->{
      flMotor.set(0);
      frMotor.set(0);
    });
  }
}
