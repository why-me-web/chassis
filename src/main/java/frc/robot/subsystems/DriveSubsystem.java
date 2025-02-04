// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  SparkMax flMotor = new SparkMax(DriveConstants.flSparkID, MotorType.kBrushless); 
  SparkMax blMotor = new SparkMax(DriveConstants.blSparkID, MotorType.kBrushless); 
  SparkMax frMotor = new SparkMax(DriveConstants.frSparkID, MotorType.kBrushless); 
  SparkMax brMotor = new SparkMax(DriveConstants.brSparkID, MotorType.kBrushless); 
  DifferentialDrive drive = new DifferentialDrive(flMotor,frMotor);

  public DriveSubsystem() {
    SparkBaseConfig config = new SparkMaxConfig();
    SparkBaseConfig config2 = new SparkMaxConfig();
    brMotor.configure(config.follow(frMotor), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    blMotor.configure(config2.follow(flMotor), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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
      double dspeed = speed.getAsDouble();
      double drotation = rotation.getAsDouble();
      SmartDashboard.putNumber("speed", dspeed);
      SmartDashboard.putNumber("rotaion", drotation);
      drive.arcadeDrive(speed.getAsDouble(),rotation.getAsDouble());
    }, ()->{
      flMotor.set(0);
      frMotor.set(0);
    });
  }
}
