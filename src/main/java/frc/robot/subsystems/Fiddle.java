// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Fiddle extends SubsystemBase {

  private final TalonFX fiddle;
  private final Orchestra orchestra;
  private String[] songs = { Constants.FiddleConstants.FiddleSongs.ALL_STAR, 
    Constants.FiddleConstants.FiddleSongs.CANTINA,
    Constants.FiddleConstants.FiddleSongs.WII_SONG };
  private int songIndex;

  /** Creates a new Fiddle. */
  public Fiddle() {

    fiddle = new TalonFX( Constants.FiddleConstants.fiddleID );
    fiddle.setNeutralMode(NeutralModeValue.Brake); //is this really nessis
    fiddle.setInverted(false);

    orchestra = new Orchestra();
    orchestra.addInstrument(fiddle);

    songIndex = 0;
    orchestra.loadMusic(songs[songIndex]);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber( "Time:  ", orchestra.getCurrentTime());
    SmartDashboard.putString("Current song:  ", this.songs[songIndex] );
    SmartDashboard.putBoolean("Is Playing:  ", isPlayingFiddle() );
  }

  public void playFiddle() {
    orchestra.play();
  }

  public void pauseFiddle() {
    orchestra.pause();
  }

  public void stopFiddle() {
    orchestra.stop();
  }

  public boolean isPlayingFiddle() {
    return orchestra.isPlaying();
  }

  public void loadMusic(String filepath) {
    orchestra.loadMusic(filepath);
  }

  public void nextSong() {
    songIndex = ( songIndex + 1 ) % songs.length;
    orchestra.loadMusic(songs[songIndex]);
  }
}
