// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;;

public class Climb extends SubsystemBase {
  TalonFX climbMotor = new TalonFX(22);
  
  public Climb() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.withCurrentLimits(new CurrentLimitsConfigs()
    .withStatorCurrentLimit(30)
    .withSupplyCurrentLimit(30)
    ).withMotorOutput(new MotorOutputConfigs()
    .withNeutralMode(NeutralModeValue.Brake)
    );
    climbMotor.getConfigurator().apply(config);
  }
  public Command climbCommandPositive(){
    return startEnd(() ->{
      climbMotor.set(ClimbConstants.climbSpeed);
    }, 
    () ->{
      climbMotor.set(0);
    });
  }
  public Command climbCommandNegative(){
    return startEnd(() ->{
      climbMotor.set(-(ClimbConstants.climbSpeed));
    }, 
    () ->{
      climbMotor.set(0);
    });
  }

  @Override
  public void periodic() {
    // This method will be called twice per scheduler run
  }
}
