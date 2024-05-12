package Music_interface;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BasicFuncs implements MusicPlayerOperations {
	long currentFrame;
	static Clip clip;
	
	static String State;
	
	AudioInputStream audioInputStream;
	static String filePath;
	BasicFuncs() {

	}
	public static void PlayMusic(String location) throws UnsupportedAudioFileException ,IOException , LineUnavailableException {
		filePath = location;
		try {
			File musicPath = new File(location);
			if (musicPath.exists()) {
			    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			    clip = AudioSystem.getClip(); // Assign to the static clip object
			    clip.open(audioInput);
			    clip.start();
				State = "play";
			}
		else { 
		    System.out.println("Audio file does not exist: " + musicPath.getAbsolutePath());
		}
		}
		catch(Exception e){
			System.out.println("Error loading audio file: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void play() {
		clip.start();
		State = "play";
	}
	
	public void pause() {
		if(State.equals("paused")) {
			return;
		}
		this.currentFrame = BasicFuncs.clip.getMicrosecondPosition();
		clip.stop();
		State = "paused";
		}
	
	public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if(State.equals("play")) {
			return;
		}
		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(currentFrame);
		this.play();
	}
	
	public void restart() throws UnsupportedAudioFileException,
    IOException, LineUnavailableException{
		clip.stop();
		clip.close();
		BasicFuncs.PlayMusic(filePath);
		clip.setMicrosecondPosition(0);
		this.play();
	}
	
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		currentFrame = 0L;
		clip.stop();
		clip.close();
	}
	
	public void jump(long c) throws UnsupportedAudioFileException, 
    IOException, LineUnavailableException{
		if(c > 0 && c < clip.getMicrosecondLength()) {
			clip.stop();
			clip.close();
			currentFrame = c;
			clip.setMicrosecondPosition(c);
			this.play();
		}
	}
	
	public Clip FastForward(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		pause();
		long m = clip.getMicrosecondLength();
		long newLoc = m + 10;
		clip.setMicrosecondPosition(newLoc);
		resume();
		return clip;
	}
		
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
       LineUnavailableException{  

	    	audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
	    	clip.open(audioInputStream); 
	    	clip.loop(Clip.LOOP_CONTINUOUSLY); 
	 }
}
	    






	
	
	


