package tilegame.audio;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    private Clip clip;

    public Sound(String path) {
        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            //clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    //Plays the Sound clip
    public void play() {
        clip.start();
    }
    //closes the sound file
    public void close() {
        stop();
        clip.close();
    }
    //pauses the sound clip
    public void stop() {
        clip.stop();
    }
    //loops the sound clip as many times as you want
    public void loop(int loop) {
        clip.loop(loop);
        
    }
    //reset the sound clip to the begening of the clip
    public void reset() {
        clip.setFramePosition(0);
    }
    
    //sets the sound clip volume
    //Maimum volume is 6.0206 
    //Minimum volume is -80.0
    public void setVolume(float volume){
        FloatControl gainControl;
        gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }
}
