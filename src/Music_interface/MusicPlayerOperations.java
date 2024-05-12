package Music_interface;

import javax.sound.sampled.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface MusicPlayerOperations {
    abstract void play();
    abstract void pause();
    abstract void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    abstract void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    abstract void restart() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    abstract void jump(long c) throws UnsupportedAudioFileException,
            IOException, LineUnavailableException;
    abstract Clip FastForward(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    abstract void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
}
