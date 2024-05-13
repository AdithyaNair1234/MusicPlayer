package Music_interface;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class RealMusicInterface {
    private JPanel panel1;
    private JButton PlayButton;
    private JButton stopButton;
    private JButton newSongButton;
    private JButton restartSongButton;
    static Music_interface.BasicFuncs BF = new Music_interface.BasicFuncs();
    static Music_interface.FileChooser FC = new Music_interface.FileChooser();
    public static void main(String[] args) {
        RealMusicInterface RMI = new RealMusicInterface();
        RMI.createInterface();
        try {
            String fileName = FC.openFile();
            BF.PlayMusic(fileName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        }
    public RealMusicInterface() {
        PlayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(RealMusicInterface.BF.State == "play") {
                    RealMusicInterface.BF.pause();
                }
                else {
                    try {
                        RealMusicInterface.BF.resume();
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        stopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    BF.stop();
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        newSongButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(BF.State == "play") {
                    try {
                        BF.stop();
                    }
                    catch (Exception m) {
                        System.out.println(m);
                    }
                }
                try {
                    String fileName = FileChooser.openFile();
                    BF.PlayMusic(fileName);
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });
        restartSongButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try{
                BF.restart();
                }
                catch (Exception m) {
                    System.out.println(m);
                }
            }
        });
    }
    public void createInterface() {
        JFrame frame = new JFrame("Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Music Player");
        frame.setSize(400,400);
        panel1.setLayout(new GridLayout(2,2));
        panel1.add(PlayButton);
        panel1.add(stopButton);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);
    }
}
