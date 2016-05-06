/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoboRace;

import COSC3P40.sound.*;
import javax.sound.sampled.AudioFormat;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class SoundEffects extends SoundManager {
    
    public Sound bump, destroy, victory;
//    int bitRate = 1444864;
//    int channels = 2;
//    int sampleRate = 44100;
//    int sampleSize = Math.round(bitRate / (channels * sampleRate));
    public SoundEffects() {
        // The following compiles and executes fine, but the files are not heard.
//        super(new AudioFormat(44100, 16, 2, false, false));
//        setSoundPath("./Sounds&Midi/");
//        bump = getSound("Bounce1.wav");
//        destroy = getSound("Salvatore_Kerboom.wav");
//        victory = getSound("Salvatore_HoorayYay.wav");
        //====================================
        // Default Files
        super(new AudioFormat(8000, 8, 1, false, false));
        setSoundPath("./Sounds&Midi/");
        bump = getSound("bump.wav");
        destroy = getSound("explosion.wav");
        victory = getSound("fanfare.wav");
    }
    
    public void playBump(){
        play(bump);
    }
    
    public void playDestroy(){
        play(destroy);
    }
    
    public void playVictory(){
        play(victory);
    }
}
